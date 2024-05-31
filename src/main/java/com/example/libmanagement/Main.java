package com.example.libmanagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Base64;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    public static boolean isValidDate(String dateStr) {
        // Biểu thức chính quy cho định dạng ngày tháng
        String regex = "^(\\d{2})/(\\d{2})/(\\d{4})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dateStr);

        // Kiểm tra định dạng bằng regex
        if (!matcher.matches()) {
            return false;
        }

        // Định dạng ngày tháng
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            // Chuyển đổi chuỗi thành LocalDate
            LocalDate inputDate = LocalDate.parse(dateStr, dateFormatter);
            // Lấy ngày hiện tại
            LocalDate currentDate = LocalDate.now();

            // Kiểm tra nếu ngày nhập vào trước hoặc bằng ngày hiện tại
            if (!inputDate.isAfter(currentDate)) {
                return false;
            }

            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        // Một số test case
        System.out.println(isValidDate("29/02/2024")); // True - Năm nhuận và ngày trong tương lai
        System.out.println(isValidDate("29/02/2023")); // False - Không phải năm nhuận
        System.out.println(isValidDate("31/04/2023")); // False - Tháng 4 chỉ có 30 ngày
        System.out.println(isValidDate("30/04/2023")); // False - Ngày quá khứ
        System.out.println(isValidDate("01/13/2023")); // False - Không có tháng 13
        System.out.println(isValidDate("12/12/2023")); // False - Ngày quá khứ hoặc hiện tại
        System.out.println(isValidDate("12/12/2023sada")); // False - Chuỗi không hợp lệ
        System.out.println(isValidDate("12/12/2023 00:00")); // False - Chuỗi không hợp lệ
        System.out.println(isValidDate("abc/12/2023")); // False - Chuỗi không hợp lệ
        System.out.println(isValidDate("01/06/2024")); // True - Ngày trong tương lai
    }


}
