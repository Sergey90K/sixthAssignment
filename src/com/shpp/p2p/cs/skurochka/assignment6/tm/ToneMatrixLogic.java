package com.shpp.p2p.cs.skurochka.assignment6.tm;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];
        for (int i = 0; i < toneMatrix.length; i++) {
            if (toneMatrix[i][column]) {
                for (int j = 0; j < samples[i].length; j++) {
                    result[j] += samples[i][j];
                }
            }
        }
        return normalisationSound(result);
    }

    /**
     * The method receives an array of input sound sine wave values and, using an algorithm,
     * aligns the sine wave within the range of values from -1.0 to +1.0
     * and returns the normalized array of sound sine wave values.
     *
     * @param innerSound An array of summed sound values.
     * @return An array of normalized sound wave values within the specified parameters.
     */
    private static double[] normalisationSound(double[] innerSound) {
        double[] result = new double[innerSound.length];
        double maxSoundValue = 0.0;
        for (double sound : innerSound) {
            if (Math.abs(maxSoundValue) < Math.abs(sound)) {
                maxSoundValue = sound;
            }
        }
        if (maxSoundValue == 0.0) {
            return new double[innerSound.length];
        }
        for (int i = 0; i < innerSound.length; i++) {
            result[i] = innerSound[i] / maxSoundValue;
        }
        return result;
    }
}