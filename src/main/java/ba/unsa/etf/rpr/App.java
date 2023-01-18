package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.filmoviManager;
import ba.unsa.etf.rpr.business.usersManager;
import ba.unsa.etf.rpr.domain.Film;
import ba.unsa.etf.rpr.exceptions.FilmoviException;
import org.apache.commons.cli.*;

import java.io.PrintWriter;

public class App {
    private static final Option addFilm = new Option("f", "add-film", false, "Adding new film to database (name, genre, duration)");
    private static final Option deleteFilm = new Option("delF", "delete-film", false, "Deleting a film from database (name)");
    private static final Option getFilms = new Option("getF", "get-films", false, "Printing all films from database");
    private static final Option getUsers = new Option("getU", "get-users", false, "Printing all users from database");

    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar cinema-manager-cli-jar-with-dependencies.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addFilm);
        options.addOption(deleteFilm);
        options.addOption(getFilms);
        options.addOption(getUsers);
        return options;
    }

    public static void main(String[] args) throws ParseException, FilmoviException {
        Options options = addOptions();

        CommandLineParser commandLineParser = new DefaultParser();

        CommandLine cl = commandLineParser.parse(options, args);

        if (cl.hasOption(addFilm.getOpt()) || cl.hasOption(addFilm.getLongOpt())) {
            filmoviManager fm = new filmoviManager();
            Film f = new Film();
            f.setIme(cl.getArgList().get(0));
            f.setZanr(cl.getArgList().get(1));
            f.setCijena(Integer.parseInt(cl.getArgList().get(2)));
            fm.add(f);
            System.out.println("Film successfully added to database!");
        } else if (cl.hasOption(deleteFilm.getOpt()) || cl.hasOption(deleteFilm.getLongOpt())) {
            filmoviManager fm = new filmoviManager();
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
            filmoviManager fm = new filmoviManager();
            fm.getAll().forEach(f -> System.out.println(f.getIme()));
        } else if (cl.hasOption(getUsers.getOpt()) || cl.hasOption(getUsers.getLongOpt())) {
            usersManager um = new usersManager();
            um.getAll().forEach(u -> System.out.println(u.getIme()));
        } else {
            printFormattedOptions(options);
            System.exit(-1);
        }
    }
}