import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static URL url;
    static HttpURLConnection urlConnection;
    static InputStream inputStream;
    static JSONArray result;

    public static void main(String[] args) {
        try {
            url = new URL("https://chocolife.me/mobileapi/v3_3/deals?town_id=1&category_id=1&page=1");
            urlConnection = (HttpURLConnection) url.openConnection();
            inputStream = urlConnection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        try {
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            result = jsonObject.getJSONArray("result");

            for (int i = 0; i < result.length(); i++) {
                System.out.println(i + 1 + "-" + result.getJSONObject(i).getString("title_short"));
            }
            System.out.println("Введите номер акции ");
            int choose = choose();
            JSONArray places = result.getJSONObject(choose).getJSONArray("places");
            String adress = places.getJSONObject(0).getString("address");
            System.out.println("Акция " + result.getJSONObject(choose).getString("title_short") + " находится по адрессу:" + adress);
        } catch (JSONException e) {
            System.out.println("Введен не верный номер акции");

        }


    }

    public static int choose() {
        try {
            int n = Integer.valueOf(scan.next());
            if (n < result.length() && n > 0) {
                return n - 1;
            }
            System.out.println("Такой акции не существует\nВведите заново");
            return choose();
        } catch (NumberFormatException ex){
            System.out.println("Такой акции не существует\nВведите заново");
            return choose();
        }
    }


}


//        File file = new File("src/file");
////        File output = new File("src/output");
////        try {
////            FileReader fileReader = new FileReader(file);
////            FileWriter outputWriter = new FileWriter(output);
////            BufferedReader bufferedReader = new BufferedReader(fileReader);
////            String line = "";
////            String[] nums1 = bufferedReader.readLine().split(" ");
////            String[] nums2 = bufferedReader.readLine().split(" ");
////
////            for (int i = 0; i < nums1.length; i++) {
////                try {
////                    int divide = Integer.valueOf(nums1[i]) / Integer.valueOf(nums2[i]);
////                    outputWriter.write(divide + " ");
////                } catch (ArithmeticException e) {
////                    outputWriter.write("На ноль делить нельзя ");
////                }
////            }
////
////            fileReader.close();
////            outputWriter.close();
////
////        } catch (FileNotFoundException e) {
////            e.printStackTrace();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }


//    static ArrayList<Notes> notes = new ArrayList<Notes>();
//    static  Notes note;
//    public static void main(String[] args) throws ParseException {
//
//        while (true) {
//            System.out.println("Введи номер операции: \n1 - Добавить \n2 - Показать \n3 - Изменить \n4 - Удалить");
//            switch (input()) {
//                case 1:
//                    note = new Notes();
//                    System.out.println("Введите заметку:");
//                    note.setNote(scan.nextLine());
//                    date(note);
//
//
//                    notes.add(note);
//                    break;
//
//                case 2:
//                    show();
//                    break;
//
//                case 3:
//                    show();
//                    int choose = Integer.parseInt(scan.nextLine()) - 1;
//                    System.out.println("Введите номер операции: \n1 - изменить заметку и дату \n2 - изменить заметку \n3 - изменить дату");
//                    note = notes.get(choose);
//                    switch (input()) {
//                        case 1:
//                            System.out.print("-------Изменить заметку и дату-------\n");
//                            System.out.println("Введите заметку");
//                            note.setNote(scan.nextLine());
//                            date(note);
//                            try {
//                                note.setCreationDate(scan.nextLine());
//                            } catch (ParseException e) {
//                                e.printStackTrace();
//                            }
//                            System.out.println("----------------------------");
//                            break;
//
//                        case 2:
//                            System.out.println("-------Изменить заметку-------");
//                            System.out.println("Введите заметку");
//                            note.setNote(scan.nextLine());
//                            break;
//
//                        case 3:
//                            System.out.println("-------Изменить дату-------");
//                            date(note);
//                            System.out.println("----------------------------");
//                            break;
//                    }
//                    break;
//
//                case 4:
//                    show();
//                    remove();
//                    break;
//            }
//        }
//    }
//
//    public static int input() {
//        try {
//            return Integer.parseInt(scan.nextLine());
//        } catch (NumberFormatException e) {
//            System.out.println("Данные введены не правильно,попробуйте снова");
//            return input();
//        }
//    }
//
//    public static int choose() {
//        return Integer.parseInt(scan.nextLine()) - 1;
//    }
//
//    public static void remove() {
//        try {
//            notes.remove(choose());
//        }catch(IndexOutOfBoundsException e){
//            System.out.println("Введен неверный номер заметки,попробуйте снова");
//            remove();
//        }
//    }
//
//    public static void show() {
//        System.out.println("--------Заметки--------");
//
//        for (int i = 0; i < notes.size(); i++) {
//            System.out.println((i + 1) + ")" + notes.get(i));
//        }
//        System.out.println("--------------------------------");
//    }
//
//    public static void date(Notes changeNote){
//        System.out.println("Введите дату(dd-mm-yyyy)");
//        try {
//            changeNote.setCreationDate(scan.nextLine());
//        } catch (ParseException e) {
//            System.out.println("Введен не правильный формат даты,попробуйте снова");
//            date(changeNote);
//        }
//    }
//}

