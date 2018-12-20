import org.jacop.constraints.*;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.*;

public class UrbanPlanning {

	public static void main(String[] args) {
		

	}

	public void solve(int n, int n_commercial, int n_residential, int[] point_distribution) {
		Store store = new Store();

		IntVar[][] grid = new IntVar[n][n];
		IntVar[] rows = new IntVar[n];
		IntVar[] cols = new IntVar[n];

		for (int i = 0; i < n; i++) {
			rows[i] = new IntVar(store, 0, n);
			cols[i] = new IntVar(store, 0, n);
		}

		for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = new IntVar(store, 0, 1);
            }
        }

		for (int i = 0; i < n; i++) {
			store.impose(new Count(grid[i], rows[i], 0));
			store.impose(new Count(getColumn(grid, i), cols[i], 0));
		}
		
		SumInt sum = new SumInt(rows);
	}
	
	public void solveForInputs(int inputSetNr) {
        switch (inputSetNr) {
            case 1:
            	int n1 = 5;
            	int n_commercial1 = 13;
            	int n_residential1 = 12;
            	int[] point_distribution1 = {-5, -4, -3, 3, 4, 5};
                solve(n1, n_commercial1, n_residential1, point_distribution1);
                break;
            case 2:
            	int n2 = 5;
            	int n_commercial2 = 7;
            	int n_residential2 = 18;
            	int[] point_distribution2 = {-5, -4, -3, 3, 4, 5};
                solve(n2, n_commercial2, n_residential2, point_distribution2);
                break;
            case 3:
            	int n3 = 7;
            	int n_commercial3 = 20;
            	int n_residential3 = 29;
            	int[] point_distribution3 = {-7, -6, -5, -4, 4, 5, 6, 7};
                solve(n3, n_commercial3, n_residential3, point_distribution3);
                break;
            default:
                System.err.println("Input not valid");
        }
    }
	
    private IntVar[] getColumn(IntVar[][] matrix, int i) {
        IntVar[] col = new IntVar[matrix.length];

        for (int j = 0; j < matrix.length; j++) {
            col[j] = matrix[j][i];
        }

        return col;
    }
    
    private static IntVar[] vectorizeIntVar(IntVar[][] matrix) {
        IntVar[] vector = new IntVar[matrix.length * matrix[0].length];
        int index = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                vector[index] = matrix[i][j];
                index++;
            }
        }
        return vector;
    }
}
