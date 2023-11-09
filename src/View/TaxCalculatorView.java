/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.text.DecimalFormat;

public class TaxCalculatorView {
    private DecimalFormat decimalFormat;
    public TaxCalculatorView() {
        this.decimalFormat = new DecimalFormat("#,###.##");
    }

    /**
     * Hiển thị menu và chờ người dùng chọn.
     */
    public void printTaxDetails(String totalIncome, double selfDeduction, double childrenDeduction, double parentsDeduction, String taxableIncome, String tax) {
        System.out.println("Tổng thu nhập: " + formatNumber(totalIncome));
        System.out.println("Giảm trừ bản thân: " + formatNumber(String.valueOf(selfDeduction)));
        System.out.println("Giảm trừ cho con cái: " + formatNumber(String.valueOf(childrenDeduction)));
        System.out.println("Giảm trừ cho bố/mẹ: " + formatNumber(String.valueOf(parentsDeduction)));
        System.out.println("Thu nhập chịu thuế: " + formatNumber(taxableIncome));
        System.out.println("Số thuế phải trả: " + formatNumber(tax));
    }

    /**
     * Định dạng số theo định dạng mong muốn và xử lý lỗi parsing.
     */
    private String formatNumber(String number) {
        try {
            // Loại bỏ tất cả các ký tự không phải số hoặc dấu âm
            String cleanedNumber = number.replaceAll("[^\\d.]", "");

            // Chuyển đổi số và định dạng nó bằng DecimalFormat
            double parsedNumber = Double.parseDouble(cleanedNumber);
            return decimalFormat.format(parsedNumber);
        } catch (NumberFormatException e) {
            // Xử lý ngoại lệ (ví dụ: ghi log, hiển thị thông báo lỗi)
            System.err.println("Lỗi khi phân tích số: " + number);
            return number; // Trả về số ban đầu trong trường hợp có lỗi
        }
    }
}

