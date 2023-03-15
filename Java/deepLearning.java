import java.lang.reflect.Array;
import java.util.Arrays;

public class deepLearning {


    /*** convolutionalCalculation
     * @author easyPeasy
     * @version 1.0
     * @update 13/3/2023
     * we just consider the input image is a square matrix, the same as the filter matrix
     * if i have more difficult about convolution, i will update this function
     */

    public static int[][] convolutionalCalculation (int[][] input, int[][] filter){
        int row = input.length;
        int rowF = filter.length;
        int ans[][] = new int[row - rowF + 1][row - rowF + 1];
        for (int i = 0; i < row - rowF + 1; i++) {
            for (int j = 0; j < row - rowF + 1; j++) {
                int sum = 0;
                for (int k = 0; k < rowF; k++) {
                    for (int l = 0; l < rowF; l++) {
                        sum += input[i+k][j+l]* filter[k][l];
                    }
                }
                ans[i][j] = sum;
            }
        }
        return ans;
    }

    /*** maxPool
     * @author easyPeasy
     * @version 1.0
     * @update 13/3/2023
     *
     *  this is a single version of maxPool without padding
     */

    public static int[][] maxPool(int[][] input,int filterSize, int filterStride){
        int row = input.length;
        int targetIndex = (row - filterSize + filterStride)/filterStride;
        int ans[][] = new int[targetIndex][targetIndex];
        int iIndex = 0;
        for (int i = 0; i < row; i += filterStride) {
            int jIndex = 0;
            for (int j = 0; j < row; j += filterStride) {
                int max = 0;
                for (int k = 0; k < filterSize; k++) {
                    for (int l = 0; l < filterSize; l++) {
                        max = Math.max(input[i+k][j+l],max);
                    }
                }
                ans[iIndex][jIndex++] = max;
            }
            iIndex++;
        }
        return ans;
    }


    /*** avgPool
     * @author easyPeasy
     * @version 1.0
     * @update 13/3/2023
     *
     *  this is a single version of avgPool without padding
     */

    public static double[][] avgPool(int[][] input,int filterSize, int filterStride){
        int row = input.length;
        int targetIndex = (row - filterSize + filterStride)/filterStride;
        double ans[][] = new double[targetIndex][targetIndex];
        int iIndex = 0;
        for (int i = 0; i < row; i += filterStride) {
            int jIndex = 0;
            for (int j = 0; j < row; j += filterStride) {
                double sum = 0;
                for (int k = 0; k < filterSize; k++) {
                    for (int l = 0; l < filterSize; l++) {
                        sum += input[i+k][j+l];
                    }
                }
                ans[iIndex][jIndex++] = sum / (filterSize*filterSize);
            }
            iIndex++;
        }
        return ans;
    }
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(convolutionalCalculation(new int[][]{
                        {0, 0, 0, 0, 0, 0},
                        {0, 1, 0, 0, 1, 0},
                        {0, 1, 0, 0, 1, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 1, 1, 1, 1, 0},
                        {0, 0, 0, 0, 0, 0},},
                new int[][]{{1, 1},
                        {0, 0}})));

        System.out.println(Arrays.deepToString(maxPool(new int[][]{
                        {0, 0, 0, 0, 0, 0},
                        {0, 1, 0, 0, 1, 0},
                        {0, 1, 0, 0, 1, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 1, 1, 1, 1, 0},
                        {0, 0, 0, 0, 0, 0},},2,2 )));

        System.out.println(Arrays.deepToString(avgPool(new int[][]{
                {0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 0},
                {0, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0},},3,3 )));
    }

}
