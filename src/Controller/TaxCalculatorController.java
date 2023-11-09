/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author sonhu
 */
import Model.TaxCalculatorModel;
import View.TaxCalculatorView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class TaxCalculatorController {
    private TaxCalculatorModel model;
    private TaxCalculatorView view;

    public TaxCalculatorController(TaxCalculatorModel model, TaxCalculatorView view) {
        this.model = model;
        this.view = view;
    }

    /* Phương thức này nhận đầu vào từ người dùng, thiết lập thuộc tính cho mô hình, và cập nhật tầm nhìn.*/
    public void calculateAndPrintTax() {
        // Nhập tổng thu nhập và các chi tiết cần thiết khác
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập tổng thu nhập: ");
        double totalIncome = scanner.nextDouble();

        System.out.print("Nhập số lượng con cái: ");
        int numberOfChildren = scanner.nextInt();
        double[] childrenAges = new double[numberOfChildren];
        for (int i = 0; i < numberOfChildren; i++) {
            System.out.print("Nhập tuổi của con " + (i + 1) + ": ");
            childrenAges[i] = scanner.nextDouble();
        }

        System.out.print("Nhập số lượng bố/mẹ: ");
        int numberOfParents = scanner.nextInt();
        double[] parentAges = new double[numberOfParents];
        for (int i = 0; i < numberOfParents; i++) {
            System.out.print("Nhập tuổi của bố/mẹ " + (i + 1) + ": ");
            parentAges[i] = scanner.nextDouble();
        }
        // Thiết lập thuộc tính cho mô hình
        model.setTotalIncome(totalIncome);
        model.setNumberOfChildren(numberOfChildren);
        model.setChildrenAges(childrenAges);
        model.setNumberOfParents(numberOfParents);
        model.setParentAges(parentAges);

        // Cập nhật và in chi tiết thuế
        updateView();
    }

    /* Phương thức này tính toán giảm trừ phụ thuộc và cập nhật tầm nhìn.*/
    private double calculateDependentDeductions() {
        double selfDeduction = calculateDeductionForSelf();
        double childrenDeduction = calculateChildrenDeduction();
        double parentsDeduction = calculateParentsDeduction();
        return selfDeduction + childrenDeduction + parentsDeduction;
    }

    /* Phương thức này tính toán giảm trừ cho con cái dựa trên độ tuổi của chúng.*/
    private double calculateChildrenDeduction() {
        double childrenDeduction = 0;
        int numberOfChildren = model.getNumberOfChildren();
        double[] childrenAges = model.getChildrenAges();

        if (numberOfChildren > 0) {
            int maxChildrenToConsider = Math.min(numberOfChildren, 2);
            double[] sortedChildrenAges = Arrays.stream(childrenAges)
                    .filter(age -> age <= 22)
                    .boxed()
                    .sorted()
                    .mapToDouble(Double::doubleValue)
                    .toArray();

            for (int i = 0; i < maxChildrenToConsider; i++) {
                if (sortedChildrenAges[i] <= 18) {
                    childrenDeduction += 4400000;
                } else if (sortedChildrenAges[i] <= 22) {
                    childrenDeduction += 6000000;
                }
            }
        }
        return childrenDeduction;
    }

    /* Phương thức này tính toán giảm trừ cho bố/mẹ nếu chỉ có một người.*/
    private double calculateParentsDeduction() {
        double parentsDeduction = 0;
        int numberOfParents = model.getNumberOfParents();
        double[] parentAges = model.getParentAges();

        if (numberOfParents == 1) {
            double totalParentsIncome = Arrays.stream(parentAges).sum();
            if (totalParentsIncome > 4000000) {
                parentsDeduction = 4400000;
            }
        }
        return parentsDeduction;
    }

    /*Phương thức này trả về giảm trừ cho bản thân (giả sử đã có logic cụ thể*/
    private double calculateDeductionForSelf() {
        return 11000000; // Thay thế với logic cụ thể cho giảm trừ bản thân
    }

    /* Phương thức này tính toán số thuế dựa trên thu nhập chịu thuế.*/
    private double calculateTaxAmount(double taxableIncome) {
        if (taxableIncome < 11000000) {
            return 0;
        } else if (taxableIncome < 4000000) {
            return taxableIncome * 0.05;
        } else if (taxableIncome <= 6000000) {
            return 200000 + (taxableIncome - 4000000) * 0.08;
        } else if (taxableIncome <= 10000000) {
            return 560000 + (taxableIncome - 6000000) * 0.1;
        } else {
            return 1560000 + (taxableIncome - 10000000) * 0.2;
        }
    }

    /*Phương thức này cập nhật tầm nhìn với thông tin thuế đã tính toán.*/
    public void updateView() {
        double deductions = calculateDependentDeductions();

        // Sử dụng DecimalFormat để định dạng số
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
        decimalFormat.applyPattern("#,##0.00");

        String formattedTotalIncome = decimalFormat.format(model.getTotalIncome());
        String formattedDeductions = decimalFormat.format(deductions);
        String formattedTaxableIncome = decimalFormat.format(model.getTotalIncome() - deductions);
        String formattedTax = decimalFormat.format(calculateTaxAmount(model.getTotalIncome() - deductions));

        view.printTaxDetails(
                formattedTotalIncome,
                calculateDeductionForSelf(),
                calculateChildrenDeduction(),
                calculateParentsDeduction(),
                formattedTaxableIncome,
                formattedTax
        );
    }
}