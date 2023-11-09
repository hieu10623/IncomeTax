/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;


/**
 *
 * @author sonhu
 */
import Controller.TaxCalculatorController;
import Model.TaxCalculatorModel;
import View.TaxCalculatorView;

public class Main {
    public static void main(String[] args) {
        // Khởi tạo model và view
        TaxCalculatorModel model = new TaxCalculatorModel();
        TaxCalculatorView view = new TaxCalculatorView();

        // Khởi tạo controller
        TaxCalculatorController controller = new TaxCalculatorController(model, view);

        // Tính toán và in thông tin thuế
        controller.calculateAndPrintTax();
    }
}

