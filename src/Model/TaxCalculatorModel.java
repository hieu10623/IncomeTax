package Model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class TaxCalculatorModel {
    private double totalIncome;
    private int numberOfChildren;
    private double[] childrenAges;
    private int numberOfParents;
    private double[] parentAges;

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public void setChildrenAges(double[] childrenAges) {
        this.childrenAges = childrenAges;
    }

    public void setNumberOfParents(int numberOfParents) {
        this.numberOfParents = numberOfParents;
    }

    public void setParentAges(double[] parentAges) {
        this.parentAges = parentAges;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public double[] getChildrenAges() {
        return childrenAges;
    }

    public int getNumberOfParents() {
        return numberOfParents;
    }

    public double[] getParentAges() {
        return parentAges;
    }
}