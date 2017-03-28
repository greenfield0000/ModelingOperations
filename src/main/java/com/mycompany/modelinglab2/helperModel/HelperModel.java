/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modelinglab2.helperModel;

import com.mycompany.modelinglab2.helperModel.ExcelHelper.ExcelHelper;
import java.util.ArrayList;

/**
 *
 * @author roma
 */
public class HelperModel {

    private final double k2 = 0.001;
    private final double m = 0.7;
    private final double A = k2 / m;

    public void getExperiment(double v0, double alpa, double h, double dt) {

        final double g = 9.8;
        double y = h;
        double x = 0.0;
        double ax = 0.0;
        double ay = 0.0;
        double vx = 0.0;
        double vy = 0.0;
        double v = 0.0;
        double v2 = 0.0;
        double nCalc = 0.0;

        vx = v0 * Math.cos(Math.toRadians(alpa));
        vy = v0 * Math.sin(Math.toRadians(alpa));

        ExcelHelper helper = new ExcelHelper();

        ArrayList<Double> distanceList = new ArrayList<>();
        ArrayList<Double> higthList = new ArrayList<>();

        double time = 0;
        while (y >= 0) {

            distanceList.add(x);
            higthList.add(y);

            v2 = Math.pow(vx, 2) + Math.pow(vy, 2);
            v = Math.sqrt(v2);
            ax = -A * v * vx;
            ay = -g - A * v * vy;
            vx += ax * dt;
            vy += ay * dt;
            x += vx * dt;
            y += vy * dt;
            nCalc++;
        }

        time += dt * nCalc;

        double maxValueDistance = 0.0;
        double maxValueHight = 0.0;

        for (Double value : distanceList) {
            if (value > maxValueDistance) {
                maxValueDistance = value;
            }
        }

        for (Double value : higthList) {
            if  (value > maxValueHight) {
                maxValueHight = value;
            }
        }

        System.out.println("Количесто реализаций " + nCalc);
        System.out.println("Время = " + time);
        System.out.println("Расстояние = " + maxValueDistance);
        System.out.println("Высота = " + maxValueHight);

        helper.formExcelFile(distanceList, higthList);

    }
}
