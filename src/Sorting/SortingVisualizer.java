package Sorting;

public class SortingVisualizer {

    private static Thread sortingThread;

    public static Frame frame;
    public static Integer[] toBeSorted;
    public static boolean isSorting = false;
    public static int toBeSortedSize = 50;
    public static int sleep = 10;
    public static int blockWidth;


    public static void main(String[] args) {
        frame = new Frame();
        resetArray();
        frame.setLocationRelativeTo(null);
    }

    //Resets toBeSorted, creating a new random array
    public static void resetArray(){
        toBeSorted = new Integer[toBeSortedSize];
        blockWidth = (int) Math.max(Math.floor(500/ toBeSortedSize), 1);
        for(int i = 0; i<toBeSorted.length; i++){
            toBeSorted[i] = (int) (toBeSortedSize *Math.random());
        }
        frame.preDrawArray(toBeSorted);
    }

    //Starts Sorting Visualization, based on what sorting algorithm is currently selected
    public static void startSort(String type){
        if (sortingThread == null || !isSorting){
            resetArray();
            isSorting = true;

            switch(type){
                case "Bubble":
                    sortingThread = new Thread(new BubbleSort(toBeSorted, frame, false));
                    break;

                case "Bubble(fast)":
                    sortingThread = new Thread(new BubbleSort(toBeSorted, frame, true));
                    break;

                case "Selection":
                    sortingThread = new Thread(new SelectionSort(toBeSorted, frame, false));
                    break;

                case "Selection(fast)":
                    sortingThread = new Thread((new SelectionSort(toBeSorted, frame, true)));
                    break;

                case "Insertion":
                    sortingThread = new Thread(new InsertionSort(toBeSorted, frame, false));
                    break;

                case "Insertion(fast)":
                    sortingThread = new Thread((new InsertionSort(toBeSorted, frame, true)));
                    break;

                default:
                    isSorting = false;
                    return;
            }

            sortingThread.start();
        }
    }
}
