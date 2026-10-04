package com.envtory.backend_api.service;

import com.envtory.backend_api.model.Lumber;
import org.springframework.stereotype.Service;

@Service
public class LumberMathService {
    
    //The toggle that remembers which way to round the next .5 fraction
    private boolean roundUpNextHalf = true;

    public void calculateAndSetFootage(Lumber board) {

        //Step 1: calculate surface measure if the user isnt using a grading stick
        if (board.getSurfaceMeasure() == null || board.getSurfaceMeasure() == 0) {
            if (board.getWidth() != null && board.getLength() != null) {
                double exactSurface = (board.getWidth() * board.getLength()) / 12.0;

                int finalSM = applyNHLARounding(exactSurface);
                board.setSurfaceMeasure(finalSM);
            }
        }

        //Step 2: calculate board footage
        if (board.getSurfaceMeasure() != null && board.getThickness() > 0) {
            double bf = board.getSurfaceMeasure() * board.getThickness();
            board.setBoardFootage(bf);
        }

    }

    private int applyNHLARounding(double exactValue) {

        double fractionalPart = exactValue % 1;

        //if the decimal is exactly .5
        if (Math.abs(fractionalPart - 0.5) < 0.0001) {
            int rounded;
            if (roundUpNextHalf) {
                rounded = (int) Math.ceil(exactValue);
            } else {
                rounded = (int) Math.floor(exactValue);
            }
            roundUpNextHalf = !roundUpNextHalf;
            return rounded;
        }

        return (int) Math.round(exactValue);
    }
}
