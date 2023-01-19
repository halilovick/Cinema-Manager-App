package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.filmoviManager;
import ba.unsa.etf.rpr.business.karteManager;
import ba.unsa.etf.rpr.business.usersManager;
import ba.unsa.etf.rpr.domain.Film;
import ba.unsa.etf.rpr.domain.Karta;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.FilmoviException;
import org.apache.commons.cli.*;

import java.io.PrintWriter;

public class App {
    private static final Option addFilm = new Option("f", "add-film", false, "Adding a new film to database (\"name\", \"genre\", duration)");
    private static final Option addUser = new Option("u", "add-user", false, "Adding a new user to database (\"username\", \"password\", admin(true/false))");
    private static final Option deleteFilm = new Option("delF", "delete-film", false, "Deleting a film from database (\"name\")");
    private static final Option getFilms = new Option("getF", "get-films", false, "Printing all films from database");
    private static final Option deleteTickets = new Option("delT", "delete-tickets", false, "Deleting existing tickets (ticket id to delete single ticket or \"name\" of film to delete all related tickets)");
    private static final Option getUsers = new Option("getU", "get-users", false, "Printing all users from database");
    private static final Option deleteUser = new Option("delU", "delete-user", false, "Deleting a user from database (user-id)");
    private static final Option getTickets = new Option("getT", "get-tickets", false, "Printing all tickets from database");
    private static final Option addTicket = new Option("t", "add-ticket", false, "Adding a new ticket to database (user-id, film-id)");

    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar cinema-manager-cli-jar-with-dependencies.jar [option] (parameters)");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addFilm);
        options.addOption(deleteFilm);
        options.addOption(getFilms);
        options.addOption(deleteTickets);
        options.addOption(getUsers);
        options.addOption(getTickets);
        options.addOption(deleteUser);
        options.addOption(addUser);
        options.addOption(addTicket);
        return options;
    }

    private static boolean isDigit(String s) {
        try {
            int intValue = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) throws ParseException, FilmoviException {
        Options options = addOptions();
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine cl = commandLineParser.parse(options, args);
        filmoviManager fm = new filmoviManager();
        karteManager km = new karteManager();
        usersManager um = new usersManager();
        if (cl.hasOption(addFilm.getOpt()) || cl.hasOption(addFilm.getLongOpt())) {
            Film f = new Film();
            f.setIme(cl.getArgList().get(0));
            f.setZanr(cl.getArgList().get(1));
            if (!isDigit(cl.getArgList().get(2))) {
                System.out.println("You must enter a valid price!");
                return;
            }
            f.setCijena(Integer.parseInt(cl.getArgList().get(2)));
            fm.add(f);
            System.out.println("Film successfully added to database!");
        } else if (cl.hasOption(deleteFilm.getOpt()) || cl.hasOption(deleteFilm.getLongOpt())) {
            Film f = new Film();
            try {
                f = fm.getByIme(cl.getArgList().get(0));
                fm.delete(f.getId());
                System.out.println("Film successfuly deleted from database!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Film with that name does not exist.");
            } catch (FilmoviException e) {
                System.out.println("Film cannot be deleted. First delete related tickets before deleting film.");
            }
        } else if (cl.hasOption(getFilms.getOpt()) || cl.hasOption(getFilms.getLongOpt())) {
            fm.getAll().forEach(f -> System.out.println(f.getIme()));
        } else if (cl.hasOption(deleteTickets.getOpt()) || cl.hasOption(deleteTickets.getLongOpt())) {
            if (isDigit(cl.getArgList().get(0))) {
                try {
                    km.delete(Integer.parseInt(cl.getArgList().get(0)));
                    System.out.println("Ticket successfully deleted from database!");
                } catch (FilmoviException f) {
                    System.out.println("Ticket does not exist!");
                }
            } else {
                try {
                    km.deleteWithFilmId(fm.getByIme(cl.getArgList().get(0)).getId());
                    System.out.println("Tickets successfully deleted from database!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Film with that name does not exist.");
                }
            }
        } else if (cl.hasOption(getUsers.getOpt()) || cl.hasOption(getUsers.getLongOpt())) {
            um.getAll().forEach(u -> System.out.println(u.getIme()));
        } else if (cl.hasOption(getTickets.getOpt()) || cl.hasOption(getTickets.getLongOpt())) {
            km.getAll().forEach(k -> System.out.println("User:" + k.getUser().getIme() + "; Film: " + k.getFilm().getIme()));
        } else if (cl.hasOption(deleteUser.getOpt()) || cl.hasOption(deleteUser.getLongOpt())) {
            if (!isDigit(cl.getArgList().get(0))) {
                System.out.println("You must enter a valid user id!");
                return;
            }
            try {
                um.delete(Integer.parseInt(cl.getArgList().get(0)));
                System.out.println("User deleted successfully!");
            } catch (FilmoviException f) {
                System.out.println("User does not exist!");
            }
        } else if (cl.hasOption(addUser.getOpt()) || cl.hasOption(addUser.getLongOpt())) {
            if (!cl.getArgList().get(2).equals("1") && !cl.getArgList().get(2).equals("0") && !cl.getArgList().get(2).equals("true") && !cl.getArgList().get(2).equals("false")) {
                System.out.println("You must enter a valid boolean value!");
                return;
            }
            User u = new User();
            u.setUser(cl.getArgList().get(0));
            u.setPassword(cl.getArgList().get(1));
            u.setAdmin(Boolean.parseBoolean(cl.getArgList().get(2)));
            um.add(u);
            System.out.println("User successfully added!");
        } else if (cl.hasOption(addTicket.getOpt()) || cl.hasOption(addTicket.getLongOpt())) {
            if (!isDigit(cl.getArgList().get(0))) {
                System.out.println("You must enter a valid film id!");
                return;
            }
            if (!isDigit(cl.getArgList().get(1))) {
                System.out.println("You must enter a valid user id!");
                return;
            }
            try {
                Karta k = new Karta();
                k.setFilm(fm.getById(Integer.parseInt(cl.getArgList().get(0))));
                k.setUser(um.getById(Integer.parseInt(cl.getArgList().get(1))));
                km.add(k);
                System.out.println("Ticket successfuly added!");
            } catch (FilmoviException f) {
                System.out.println("User/film does not exist!");
            }
        } else {
            printFormattedOptions(options);
            System.exit(-1);
        }
    }
}