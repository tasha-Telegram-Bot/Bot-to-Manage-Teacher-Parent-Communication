import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelegramBot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {
        String command = update.getMessage().getText();
        //connect database
        SendMessage message = new SendMessage().setParseMode("Markdown");
        ConnectionBot cm = new ConnectionBot();
        Connection connection = cm.getConnection();
        String username = update.getMessage().getFrom().getUserName();
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        String subjectclass="";
        String kelas="";



        if (update.hasMessage() && update.getMessage().hasText()) {
                if (command.equals("/start")) {
                    try {

                        Statement stmt = connection.createStatement();
                        System.out.println("username : " + username);
                        ResultSet rs = stmt.executeQuery("select username from teacher where username=" + "'" + username + "'");

                        if (rs.next()) {
                            message.setText("Hi, teacher "+ update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName()+"\uD83E\uDD29 To get started, type or click */* to view list of instructions.");
                            System.out.println("start success!");

                        } else {
                            message.setText("You're enroll as a parent, " + update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName()+"\uD83E\uDD29 To get started, type or click */* to view list of instructions.");
                        }


                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }else if (command.equals("Add Homework")){
                    message.setText("✍\uD83C\uDFFCEnter your Homework using this format✍\uD83C\uDFFC" +
                            "\n\n*SubjectClass-tarikh|nama buku|muka surat|nombor soalan#*\n\uD83D\uDCDA*Note: One subject only. Make sure you're follow format given.*"+
                            "\n\n\uD83D\uDC47\uD83C\uDFFBExample\nSejarah2B-13/05/2020|Buku Latihan|m/s 20|soalan 2a,2b#" +
                            "\nBahasaArab3A-20/11/2020|Buku text Sejarah|m/s 412|soalan 1,2#");


                }else if (command.equals("Add Schedule")){
                    message.setText("✍\uD83C\uDFFCEnter your Schedule using this format✍\uD83C\uDFFC" +
                            "\n\n*Class-subject(time)|subject(time)$*\n\uD83D\uDCDA*Note: One class only. Make sure you're follow format given.*"+
                            "\n\nExample:\n3A-BM(10pm)|rehat|SJ(12pm)|SC(3pm)$");


                }else if (command.equals("Add Reminder")){
                    message.setText("✍\uD83C\uDFFCEnter your Reminder using this format✍\uD83C\uDFFC" +
                            "\n\n*SubjectClass-date|reminder%*\n\uD83D\uDCDA*Note: One subject only. Make sure you're follow format given.*"+
                            "\n\n\uD83D\uDC47\uD83C\uDFFBExample\nSejarah2B-13/05/2020|pakai baju sukan esok%");


                }else if (command.equals("Edit Schedule")){
                    message.setText("✍\uD83C\uDFFCEdit your Schedule using this format✍\uD83C\uDFFC" +
                            "\n\n*Class-subject(time)|subject(time)@*\n\uD83D\uDCDA*Note: One class only. Make sure you're follow format given.*"+
                            "\n\nExample:\n3A-BM(10pm)|rehat|SJ(12pm)|SC(3pm)@");


                }else if (command.equals("Edit Homework")){
                    message.setText("✍\uD83C\uDFFCEdit your Homework using this format✍\uD83C\uDFFC" +
                            "\n\n*SubjectClass-tarikh|nama buku|muka surat|nombor soalan+*\n\uD83D\uDCDA*Note: One subject only. Make sure you're follow format given.*"+
                            "\n\n\uD83D\uDC47\uD83C\uDFFBExample\nSejarah2B-13/05/2020|Buku Latihan|m/s 20|soalan 2a,2b+" +
                            "\nBahasaArab3A-20/11/2020|Buku text Sejarah|m/s 412|soalan 1,2+");


                }else if (command.equals("Edit Reminder")){
                    message.setText("✍\uD83C\uDFFCEnter your Reminder using this format✍\uD83C\uDFFC" +
                            "\n\n*SubjectClass-date|reminder&*\n\uD83D\uDCDA*Note: One subject only. Make sure you're follow format given.*"+
                            "\n\n\uD83D\uDC47\uD83C\uDFFBExample\nSejarah2B-13/05/2020|pakai baju sukan esok&");



                }else if (command.equals("Delete Schedule")){
                    message.setText("✍\uD83C\uDFFCDelete your Schedule using this format✍\uD83C\uDFFC" +
                            "\n\n*Class-^^*\n\uD83D\uDCDA*Note: One class only. Make sure you're follow format given.*"+
                            "\n\nExample:\n3A-^^");



                }else if (command.equals("Delete Homework")){
                    message.setText("✍\uD83C\uDFFCDelete your Homework using this format✍\uD83C\uDFFC" +
                            "\n\n*SubjectClass-*\n\uD83D\uDCDA*Note: One subject only. Make sure you're follow format given.*"+
                            "\n\n\uD83D\uDC47\uD83C\uDFFBExample\nSejarah2B-!" +
                            "\nBahasaArab3A-!");

                }else if (command.equals("Delete Reminder")){
                    message.setText("✍\uD83C\uDFFCDelete your Reminder using this format✍\uD83C\uDFFC" +
                            "\n\n*SubjectClass-<*\n\uD83D\uDCDA*Note: One subject only. Make sure you're follow format given.*"+
                            "\n\n\uD83D\uDC47\uD83C\uDFFBExample\nSejarah2B-<");

                }else if (command.equals("View Schedule")){
                    message.setText("✍\uD83C\uDFFCView your Schedule using this format✍\uD83C\uDFFC" +
                            "\n\n*-class-school ID*\n\uD83D\uDCDA*Note: One subject only. Make sure you're follow format given.*"+
                            "\n\n\uD83D\uDC47\uD83C\uDFFBExample\n-3b-1");

                }else if (command.equals("View Reminder")){
                    message.setText("✍\uD83C\uDFFCView your Reminder using this format✍\uD83C\uDFFC" +
                            "\n\n*%SubjectClass%school ID*\n\uD83D\uDCDA*Note: One subject only. Make sure you're follow format given.*"+
                            "\n\n\uD83D\uDC47\uD83C\uDFFBExample\n%Sejarah2B%1");

                }else if (command.equals("View Homework")){
                    message.setText("✍\uD83C\uDFFCView your Homework using this format✍\uD83C\uDFFC" +
                            "\n\n*>SubjectClass>school ID*\n\uD83D\uDCDA*Note: One subject only. Make sure you're follow format given.*"+
                            "\n\n\uD83D\uDC47\uD83C\uDFFBExample\n>Sejarah2B>1");

                }else if (command.startsWith("%")) {//view reminder
                    String[] viewReminder = command.split("%");
                    subjectclass = viewReminder[1];
                    System.out.println(subjectclass);
                    System.out.print(viewReminder[2]);

                    try {
                        Statement stmt = connection.createStatement();
                        ResultSet rs1=stmt.executeQuery("select reminder from subjectclass where school_id='"+viewReminder[2]+"' and subjectclass='"+subjectclass+"'");
                        if (rs1.next()) {
                            message.setText("*Reminder "+subjectclass+"*\uD83D\uDC47\uD83C\uDFFB\n\n"+rs1.getString(1));
                        }else {
                            message.setText("empty");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        message.setText("Sorry! the reminder is null\uD83D\uDE4F\uD83C\uDFFB\nGet help by insert /help.");
                    }

                }else if (command.startsWith(">")) {//view homework
                    String[] viewHomework = command.split(">");
                    subjectclass = viewHomework[1];
                    System.out.println(subjectclass);
                    System.out.print(viewHomework[2]);

                    try {
                        Statement stmt = connection.createStatement();
                        ResultSet rs1=stmt.executeQuery("select homework from subjectclass where school_id='"+viewHomework[2]+"' and subjectclass='"+subjectclass+"'");
                        if (rs1.next()) {
                            message.setText("*Homework "+subjectclass+"*\uD83D\uDC47\uD83C\uDFFB\n\n"+rs1.getString(1));
                        }else {
                            message.setText("empty");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        message.setText("Sorry! the homework is null\uD83D\uDE4F\uD83C\uDFFB\nGet help by insert /help.");
                    }

                }else if (command.startsWith("-")) {//view schedule
                    String[] viewSchedule = command.split("-");
                    kelas = viewSchedule[1];
                    System.out.println(kelas);

                    try {
                        Statement stmt = connection.createStatement();
                        ResultSet rs1=stmt.executeQuery("select schedule from schedule where school_id='"+viewSchedule[2]+"' and class='"+kelas+"'");
                        if (rs1.next()) {
                            message.setText("*Schedule "+kelas+"*\uD83D\uDC47\uD83C\uDFFB\n\n"+rs1.getString(1));
                        }else {
                            message.setText("empty");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        message.setText("Sorry! Duplicate class.You can only edit the existing class\uD83D\uDE4F\uD83C\uDFFB\nGet help by insert /help.");
                    }

                }else if (command.contains("#")) {
                    String[] Homework = command.split("-");
                    subjectclass = Homework[0];
                    System.out.println(subjectclass);

                    try {
                        Statement stmt = connection.createStatement();
                        stmt.executeUpdate("INSERT INTO subjectclass(subjectclass,school_id,username, homework) VALUES('"+subjectclass+"',(select school_id from teacher where username='"+username+"'),'"+username+"','"+Homework[1]+"')");
                        message.setText("Your *homework* have been added! Thank you\uD83D\uDE01\n\n Type using the same format if you want to enter homework the next class!\uD83D\uDC4C\uD83C\uDFFB");

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        message.setText("Sorry! Duplicate class.You can only edit the existing class\uD83D\uDE4F\uD83C\uDFFB\nGet help by insert /help.");
                    }

                }else if (command.contains("$")) {
                    String[] Schedule = command.split("-");
                    kelas= Schedule[0];
                    System.out.println(kelas);

                    try {

                        Statement stmt = connection.createStatement();
                        ResultSet rs = stmt.executeQuery("select username from teacher where username=" + "'" + username + "'");
                        if(rs.next()) {
                            stmt.executeUpdate("INSERT INTO schedule(class,school_id,username, schedule) VALUES('"+kelas+"',(select school_id from teacher where username='"+username+"'),'"+username+"','"+Schedule[1]+"')");
                            message.setText("Your *Schedule* have been added! Thank you\uD83D\uDE01\n\n Type using the same format if you want to enter schedule the next class!\uD83D\uDC4C\uD83C\uDFFB");

                        }else{
                            message.setText("You are not teacher/you enter wrong format.");
                        }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        message.setText("Sorry! Duplicate class.You can only edit the existing class\uD83D\uDE4F\uD83C\uDFFB\nGet help by insert /help.");
                    }

                }else if (command.contains("%")) {
                    String[] reminder = command.split("-");
                    subjectclass= reminder[0];
                    System.out.println(subjectclass);

                    try {

                        Statement stmt = connection.createStatement();
                        ResultSet rs = stmt.executeQuery("select username from teacher where username=" + "'" + username + "'");
                        if(rs.next()) {
                            stmt.executeUpdate("INSERT INTO subjectclass(subjectclass,school_id,username, reminder) VALUES('"+subjectclass+"',(select school_id from teacher where username='"+username+"'),'"+username+"','"+reminder[1]+"')");
                            message.setText("Your *reminder* have been added! Thank you\uD83D\uDE01\n\n Type using the same format if you want to enter reminder the next class!\uD83D\uDC4C\uD83C\uDFFB");
                        }else{
                            message.setText("You are not teacher/you enter wrong format.");

                        }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        message.setText("Sorry! Duplicate class.You can only edit the existing class\uD83D\uDE4F\uD83C\uDFFB\nGet help by insert /help.");
                    }

                }else if (command.contains("@")) {//edit schedule
                    String[] Schedule = command.split("-");
                    kelas = Schedule[0];
                    System.out.println(kelas);

                    try {

                        Statement stmt = connection.createStatement();
                        ResultSet rs = stmt.executeQuery("select username from teacher where username=" + "'" + username + "'");
                        if(rs.next()) {
                            stmt.executeUpdate("update schedule set schedule='" + Schedule[1] + "' where class='" + kelas + "'");
                            message.setText("Your *schedule* have been edited! Thank you\uD83D\uDE01\n\n Type using the same format if you want to edit schedule the next class!\uD83D\uDC4C\uD83C\uDFFB");
                        }else{
                            message.setText("sorry!Error\uD83D\uDE4F\uD83C\uDFFB\nGet help by insert /help.");
                        }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        message.setText("Sorry! Duplicate class.You can only edit the existing class\uD83D\uDE4F\uD83C\uDFFB\nGet help by insert /help.");
                    }

                }else if (command.contains("+")) { // edit homework
                    String[] Homework = command.split("-");
                    subjectclass = Homework[0];
                    System.out.println(subjectclass);

                    try {
                        Statement stmt = connection.createStatement();
                        stmt.executeUpdate("update subjectclass set homework='" + Homework[1] + "' where subjectclass='" + subjectclass + "'");
                        message.setText("Your *homework* have been edited! Thank you\uD83D\uDE01\n\n Type using the same format if you want to edit homework the next class!\uD83D\uDC4C\uD83C\uDFFB");

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        message.setText("Sorry!Error\uD83D\uDE4F\uD83C\uDFFB\nGet help by insert /help.");
                    }

                }else if (command.contains("&")) {//edit reminder
                    String[] reminder = command.split("-");
                    subjectclass= reminder[0];
                    System.out.println(subjectclass);

                    try {

                        Statement stmt = connection.createStatement();
                        ResultSet rs = stmt.executeQuery("select username from teacher where username=" + "'" + username + "'");

                        if(rs.next()) {
                            stmt.executeUpdate("update subjectclass set reminder='" + reminder[1] + "' where subjectclass='" + subjectclass + "'");
                            message.setText("Your *reminder* have been edited! Thank you\uD83D\uDE01\n\n Type using the same format if you want to edit reminder the next class!\uD83D\uDC4C\uD83C\uDFFB");
                        }else{
                            message.setText("sorry!Error\uD83D\uDE4F\uD83C\uDFFB\nGet help by insert /help.");

                        }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        message.setText("Sorry! Duplicate class.You can only edit the existing class\uD83D\uDE4F\uD83C\uDFFB\nGet help by insert /help.");
                    }

                }else if (command.contains("^^")) {//delete schedule
                    String[] schedule = command.split("-");
                    kelas= schedule[0];
                    System.out.println(kelas);

                    try {

                        Statement stmt = connection.createStatement();
                        ResultSet rs = stmt.executeQuery("select username from teacher where username=" + "'" + username + "'");

                        if(rs.next()) {
                            stmt.executeUpdate("update schedule set schedule=' ' where class='"+ kelas +"'");
                            message.setText("Your *schedule* have been deleted! Thank you\uD83D\uDE01\n\n Type using the same format if you want to delete schedule the next class!\uD83D\uDC4C\uD83C\uDFFB");
                        }else{
                            message.setText("sorry!Error\uD83D\uDE4F\uD83C\uDFFB\nGet help by insert /help.");
                        }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        message.setText("Sorry! Error\uD83D\uDE4F\uD83C\uDFFB\nGet help by insert /help.");
                    }
                }else if (command.contains("!")) {//delete homework
                    String[] Homework = command.split("-");
                    subjectclass = Homework[0];
                    System.out.println(subjectclass);

                    try {
                        Statement stmt = connection.createStatement();
                        stmt.executeUpdate("update subjectclass set homework=' ' where subjectclass='" + subjectclass + "'");
                        message.setText("Your *homework* have been deleted! Thank you\uD83D\uDE01\n\n Type using the same format if you want to delete homework the next class!\uD83D\uDC4C\uD83C\uDFFB");

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        message.setText("Sorry! Error\uD83D\uDE4F\uD83C\uDFFB\nGet help by insert /help.");
                    }

                }else if (command.contains("<")) {//delete reminder
                    String[] reminder = command.split("-");
                    subjectclass= reminder[0];
                    System.out.println(subjectclass);

                    try {

                        Statement stmt = connection.createStatement();
                        ResultSet rs = stmt.executeQuery("select username from teacher where username=" + "'" + username + "'");

                        if(rs.next()) {
                            stmt.executeUpdate("update subjectclass set reminder=' ' where subjectclass='" + subjectclass + "'");
                            message.setText("Your *Reminder* have been deleted! Thank you\uD83D\uDE01\n\n Type using the same format if you want to delete reminder the next class!\uD83D\uDC4C\uD83C\uDFFB");
                        }else{
                            message.setText("sorry!Error\uD83D\uDE4F\uD83C\uDFFB\nGet help by insert /help.");

                        }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        message.setText("sorry!Error\uD83D\uDE4F\uD83C\uDFFB\nGet help by insert /help.");
                    }

                }else if (command.equals("/generate")){


                    try {
                       Statement stmt = connection.createStatement();
                        ResultSet rs = stmt.executeQuery("select username from teacher where username=" + "'" + username + "'");
                    if (rs.next()){
                        message.setText("Choose the process \uD83D\uDC47\uD83C\uDFFB");
                        message.setReplyMarkup(replyKeyboardMarkup);
                        replyKeyboardMarkup.setSelective(true);
                        replyKeyboardMarkup.setResizeKeyboard(true);
                        replyKeyboardMarkup.setOneTimeKeyboard(false);

                        // Create a list of keyboard rows
                        List<KeyboardRow> keyboard = new ArrayList<>();

                        // First keyboard row
                        KeyboardRow Add = new KeyboardRow();
                        // Add buttons to the first keyboard row
                        Add.add(new KeyboardButton("Add"));

                        // Second keyboard row
                        KeyboardRow Edit = new KeyboardRow();
                        // Add the buttons to the second keyboard row
                        Edit.add(new KeyboardButton("Edit"));

                        // Third keyboard row
                        KeyboardRow Delete = new KeyboardRow();
                        // Add the buttons to the Third keyboard row
                        Delete.add(new KeyboardButton("Delete"));

                        // Add all of the keyboard rows to the list
                        keyboard.add(Add);
                        keyboard.add(Edit);
                        keyboard.add(Delete);

                        // and assign this list to our keyboard
                        replyKeyboardMarkup.setKeyboard(keyboard);

                        }
                    else {
                        message.setText("Choose the process \uD83D\uDC47\uD83C\uDFFB");
                        message.setReplyMarkup(replyKeyboardMarkup);
                        replyKeyboardMarkup.setSelective(true);
                        replyKeyboardMarkup.setResizeKeyboard(true);
                        replyKeyboardMarkup.setOneTimeKeyboard(false);

                        // Create a list of keyboard rows
                        List<KeyboardRow> keyboard = new ArrayList<>();

                        // First keyboard row
                        KeyboardRow viewSchedule = new KeyboardRow();
                        // Add buttons to the first keyboard row
                        viewSchedule.add(new KeyboardButton("View Schedule"));

                        // Second keyboard row
                        KeyboardRow viewHomework = new KeyboardRow();
                        // Add the buttons to the second keyboard row
                        viewHomework.add(new KeyboardButton("View Homework"));

                        // Third keyboard row
                        KeyboardRow viewReminder = new KeyboardRow();
                        // Add the buttons to the Third keyboard row
                        viewReminder.add(new KeyboardButton("View Reminder"));

                        // Add all of the keyboard rows to the list
                        keyboard.add(viewSchedule);
                        keyboard.add(viewHomework);
                        keyboard.add(viewReminder);

                        // and assign this list to our keyboard
                        replyKeyboardMarkup.setKeyboard(keyboard);

                    }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }




                }else if (command.equals("/help")){
                    try {

                        Statement stmt = connection.createStatement();
                        System.out.println("username : " + username);
                        ResultSet rs = stmt.executeQuery("select * from teacher where username=" + "'" + username + "'");

                        if (rs.next()) {
                            message.setText("\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47List of instructions \uD83D\uDC47\uD83D\uDC47\uD83D\uDC47" +
                                    "\n\n*Add*- Add homework, schedule and reminder\n*Edit*- Edit homework, schedule and reminder\n*Delete*- Delete homework, schedule and reminder");

                            System.out.println("start success!");

                        } else {
                          message.setText("\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47List of command\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\n\n*View Schedule*- Parent view schedule\n*View Reminder*- Parent view reminder\n*View Homework*-Parent view homework");
                        }


                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }


                }else if (command.equals("Add")) {


                    System.out.println("Add success");
                    message.setText("please choose:");
                    message.setReplyMarkup(replyKeyboardMarkup);
                    replyKeyboardMarkup.setSelective(true);
                    replyKeyboardMarkup.setResizeKeyboard(true);
                    replyKeyboardMarkup.setOneTimeKeyboard(false);

                    // Create a list of keyboard rows
                    List<KeyboardRow> keyboard = new ArrayList<>();
                    // First keyboard row
                    KeyboardRow keyboardAddHomework = new KeyboardRow();

                    // Add buttons to the first keyboard row
                    keyboardAddHomework.add(new KeyboardButton("Add Homework"));

                    // Second keyboard row
                    KeyboardRow keyboardAddReminder = new KeyboardRow();
                    // Add the buttons to the second keyboard row
                    keyboardAddReminder.add(new KeyboardButton("Add Reminder"));

                    // Third keyboard row
                    KeyboardRow keyboardAddSchedule = new KeyboardRow();
                    // Add the buttons to the Third keyboard row
                    keyboardAddSchedule.add(new KeyboardButton("Add Schedule"));

                    // Add all of the keyboard rows to the list
                    keyboard.add(keyboardAddHomework);
                    keyboard.add(keyboardAddSchedule);
                    keyboard.add(keyboardAddReminder);

                    // and assign this list to our keyboard
                    replyKeyboardMarkup.setKeyboard(keyboard);

                }else if (command.equals("Edit")) {
                    System.out.println("Edit success");
                    message.setText("please choose:");
                    message.setReplyMarkup(replyKeyboardMarkup);
                    replyKeyboardMarkup.setSelective(true);
                    replyKeyboardMarkup.setResizeKeyboard(true);
                    replyKeyboardMarkup.setOneTimeKeyboard(false);

                    // Create a list of keyboard rows
                    List<KeyboardRow> keyboard = new ArrayList<>();
                    // First keyboard row
                    KeyboardRow keyboardEditHomework = new KeyboardRow();

                    // Add buttons to the first keyboard row
                    keyboardEditHomework.add(new KeyboardButton("Edit Homework"));

                    // Second keyboard row
                    KeyboardRow keyboardEditReminder = new KeyboardRow();
                    // Add the buttons to the second keyboard row
                    keyboardEditReminder.add(new KeyboardButton("Edit Reminder"));

                    // Third keyboard row
                    KeyboardRow keyboardEditSchedule = new KeyboardRow();
                    // Add the buttons to the Third keyboard row
                    keyboardEditSchedule.add(new KeyboardButton("Edit Schedule"));

                    // Add all of the keyboard rows to the list
                    keyboard.add(keyboardEditHomework);
                    keyboard.add(keyboardEditReminder);
                    keyboard.add(keyboardEditSchedule);

                    // and assign this list to our keyboard
                    replyKeyboardMarkup.setKeyboard(keyboard);

                }else if (command.equals("Delete")) {
                    System.out.println("delete success");
                    message.setText("please choose:");
                    message.setReplyMarkup(replyKeyboardMarkup);
                    replyKeyboardMarkup.setSelective(true);
                    replyKeyboardMarkup.setResizeKeyboard(true);
                    replyKeyboardMarkup.setOneTimeKeyboard(false);

                    // Create a list of keyboard rows
                    List<KeyboardRow> keyboard = new ArrayList<>();
                    // First keyboard row
                    KeyboardRow keyboardDelHw = new KeyboardRow();

                    // Add buttons to the first keyboard row
                    keyboardDelHw.add(new KeyboardButton("Delete Homework"));

                    // Second keyboard row
                    KeyboardRow keyboardDelReminder = new KeyboardRow();
                    // Add the buttons to the second keyboard row
                    keyboardDelReminder.add(new KeyboardButton("Delete Reminder"));

                    // Third keyboard row
                    KeyboardRow keyboardDelSch = new KeyboardRow();
                    // Add the buttons to the Third keyboard row
                    keyboardDelSch.add(new KeyboardButton("Delete Schedule"));

                    // Add all of the keyboard rows to the list
                    keyboard.add(keyboardDelHw);
                    keyboard.add(keyboardDelReminder);
                    keyboard.add(keyboardDelSch);

                    // and assign this list to our keyboard
                    replyKeyboardMarkup.setKeyboard(keyboard);
                }

                message.setChatId(update.getMessage().getChatId());
                try {
                    execute(message);
                }catch (TelegramApiException e) {
                    e.printStackTrace();
                }

        }
    }


    public String getBotUsername() {
        return "SchoolUPMBot";
    }

    public String getBotToken() {
        return "956192222:AAEDOuRtAyk8O_6sPCRZzZgLtzOXR1kGShM";
    }
}