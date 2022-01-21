package com.company;
import java.io.*;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {


    public static Date validateDate()
    {
        Scanner scn = new Scanner(System.in);

        Date date;
        while (true)
        {
            String stringdate=scn.next();
            try{
                date=new SimpleDateFormat("yyyy/MM/dd").parse(stringdate);
                break;
            }
            catch (ParseException e)
            {
                System.out.println("Enter date in particular format : YYYY/MM/DD");
            }
        }
        return date;
    }
    public static int getnumber(){
        Scanner scn = new Scanner(System.in);
        int amount;
        while(true)
        {
            try {
                amount = Integer.parseInt(scn.next());
                break;
            }
            catch (NumberFormatException e )
            {
                System.out.print("\033[1;31m");
                System.out.println("Enter valid character only");
                System.out.print("\033[0m");
            }
        }
        return amount;

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Socket s = null;
        InputStreamReader ir = null;
        OutputStreamWriter os = null;
        BufferedReader br = null;
        BufferedWriter bw = null;
        String getusername;
        try (Socket socket = new Socket("localhost", 1234)) {
            try {


                System.out.println("client start");
                ir = new InputStreamReader(socket.getInputStream());
                os = new OutputStreamWriter(socket.getOutputStream());
                br = new BufferedReader(ir);
                bw = new BufferedWriter(os);
                while (true) {
                    System.out.println();
                    System.out.println("\uD83E\uDD11" + ".....Welcome to the mota bank...." + "\uD83E\uDD11");
                    System.out.println( "Wishing you a great day ahead"+"\033[1;33m");
                    System.out.println();
                    while (true) {
                        System.out.println("Pls select any of the below options");
                        System.out.println("1- Login new user");
                        System.out.println("2- Login Existing user");
                        System.out.println("3- Forget Password");
                        System.out.print("\033[0m");

                        int input = getnumber();
                        if (input == 1) {
                            bw.write("newuser");
                            bw.newLine();
                            bw.flush();
                            while (true) {
                                System.out.println("Enter new username");
                                String username = sc.nextLine();
                                bw.write(username);
                                bw.newLine();
                                bw.flush();

                                if (Objects.equals(br.readLine(), "valid")) {
                                    getusername = username;
                                    break;
                                }
                                System.out.print("\033[1;31m");
                                System.out.println("This username is not valid");
                                System.out.print("\033[0m");


                            }

                            System.out.println("Enter password ");
                            String password = sc.nextLine();
                            bw.write(password);
                            bw.newLine();
                            bw.flush();
                            System.out.println("Enter phone number ");
                            String phonenumber;
                            while (true) {
                                phonenumber = sc.nextLine();
                                if (String.valueOf(phonenumber).length() == 10) {
                                    break;
                                }
                                System.out.print("\033[1;31m");
                                System.out.println("Enter valid 10 digits only");
                                System.out.print("\033[0m");
                            }
                            bw.write(String.valueOf(phonenumber));
                            bw.newLine();
                            bw.flush();
                            System.out.println("Enter account type:");
                            System.out.println("1.Saving" + '\n' + "2.Current");
                            String accounttype = sc.nextLine();
                            while (true) {
                                if (!(accounttype.equals("1") || accounttype.equals("2"))) {
                                    System.out.print("\033[1;31m");
                                    System.out.println("Pls select valid options");
                                    System.out.print("\033[0m");
                                    accounttype = sc.nextLine();
                                } else {
                                    break;
                                }

                            }

                            bw.write(accounttype);
                            bw.newLine();
                            bw.flush();
                            System.out.println("\033[0;35m");
                            System.out.println("server : " + br.readLine());
                            System.out.println("\033[0m");
                            break;
                        }

                        if (input == 2) {
                            bw.write("existinguser");
                            bw.newLine();
                            bw.flush();
                            System.out.println("Pls enter existing username");
                            String username = sc.nextLine();
                            bw.write(username);
                            bw.newLine();
                            bw.flush();

                            while (true) {
                                System.out.println("Pls enter existing password");
                                String password = sc.nextLine();
                                bw.write(password);
                                bw.newLine();
                                bw.flush();
                                if (Objects.equals(br.readLine(), "valid")) {
                                    getusername = username;
                                    break;
                                }
                                System.out.print("\033[1;31m");
                                System.out.println("Wrong Password");
                                System.out.print("\033[0m");
                            }
                            System.out.println(br.readLine());
                            break;
                        }

                        if(input==3){
                            bw.write("forget");
                            bw.newLine();
                            bw.flush();

                            System.out.println("Enter the username");
                            String username = sc.nextLine();
                            bw.write(username);
                            bw.newLine();
                            bw.flush();

                            System.out.println("Enter the otp send to your registered account");
                            String otp = sc.nextLine();
                            bw.write(otp);
                            bw.newLine();
                            bw.flush();

                            System.out.println(br.readLine());
                        }

                    }
                    while (true) {
                        System.out.println("\033[1;33m");
                        System.out.println("Pls select the below appropriate options");
                        System.out.println("1. Deposit");
                        System.out.println("2. Withdraw");
                        System.out.println("3. Check balance");
                        System.out.println("4. Generate Statement ");
                        System.out.println("5. Search Transaction of particular date");
                        System.out.println("6. Transfer money");
                        System.out.println("\033[0m");

                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        int inputnew = getnumber();
                        if (inputnew == 1) {
                            bw.write("deposit");
                            bw.newLine();
                            bw.flush();
                            System.out.println(getusername);
                            bw.write(getusername);
                            bw.newLine();
                            bw.flush();

                            System.out.println("Enter the amount to be deposited");
                            String amount = sc.nextLine();
                            bw.write(amount);
                            bw.newLine();
                            bw.flush();

                            System.out.println(br.readLine());
                            break;
                        }
                        if (inputnew == 2) {
                            bw.write("withdraw");
                            bw.newLine();
                            bw.flush();
                            bw.write(getusername);
                            bw.newLine();
                            bw.flush();

                            System.out.println("Enter the amount to be withdraw");
                            String amount = sc.nextLine();
                            bw.write(amount);
                            bw.newLine();
                            bw.flush();

                            System.out.println(br.readLine());
                            break;
                        }
                        if (inputnew == 3) {
                            bw.write("balance");
                            bw.newLine();
                            bw.flush();
                            bw.write(getusername);
                            bw.newLine();
                            bw.flush();

                            System.out.println("Your new balance is :" + br.readLine());
                            break;
                        }
                        if (inputnew == 4) {
                            bw.write("generatestatement");
                            bw.newLine();
                            bw.flush();
                            bw.write(getusername);
                            bw.newLine();
                            bw.flush();
                            String s1 = br.readLine();
                            String[] arrayofstring = s1.split("@");
                            List<String> listParts = Arrays.asList(arrayofstring);
                            for (int i = 0; i < listParts.size(); i++) {
                                System.out.println(listParts.get(i));
                            }
                            break;


                        }
                        if (inputnew == 5) {
                            bw.write("search");
                            bw.newLine();
                            bw.flush();
                            bw.write(getusername);
                            bw.newLine();
                            bw.flush();

                            System.out.println("PLs select the below options");
                            System.out.println("1. Past months Transactions");
                            System.out.println("2. Past 6 months Transaction");
                            System.out.println("3. Custom Transaction");

                            int choice = getnumber();
                            if(choice==1)
                            {
                                bw.write("particular");
                                bw.newLine();
                                bw.flush();
                            }
                            else if(choice==2)
                            {
                                bw.write("particularsix");
                                bw.newLine();
                                bw.flush();
                            }
                            else if(choice==3)
                            {
                                bw.write("custom");
                                bw.newLine();
                                bw.flush();

                                System.out.println("Enter start Date");
                                Date startdate = validateDate();
                                DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
                                String strDate = dateFormat.format(startdate);

                                System.out.println("Enter end Date");
                                Date enddate = validateDate();
                                dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                                String endDate = dateFormat.format(enddate);
                                bw.write(strDate);
                                bw.newLine();
                                bw.flush();
                                bw.write(endDate);
                                bw.newLine();
                                bw.flush();

                            }
                            else
                            {
                                System.out.println("Wrong option selected. Redirecting you to the main menu");
                            }

                            var transactions = br.readLine();
                            String[] arrayofstring = transactions.split(":");
                            List<String> listParts = Arrays.asList(arrayofstring);
                            for (int i = 1; i < listParts.size(); i++) {
                                System.out.println(listParts.get(i));
                            }
                            break;
                        }
                        if (inputnew == 6) {
                            bw.write("transfer");
                            bw.newLine();
                            bw.flush();
                            bw.write(getusername);
                            bw.newLine();
                            bw.flush();

                            System.out.println("Enter the username for the account where the money will be transferred.");
                            String username = sc.nextLine();
                            System.out.println("Enter the amount to be transferred");
                            int amount = getnumber();
                            bw.write(username);
                            bw.newLine();
                            bw.flush();

                            bw.write(String.valueOf(amount));
                            bw.newLine();
                            bw.flush();

                            System.out.println(br.readLine());
                            break;
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        catch (IOException e) {
            e.printStackTrace();
        }
        }
}
