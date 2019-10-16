package Sorting;

public class SortingVisualizer {

    private static Thread sortingThread;

    public static Frame frame;
    public static Integer[] toBeSorted;
    public static boolean isSorting = false;
    public static int toBeSortedSize = 100;
    public static int sleep = 10;
    public static int blockWidth;
    // Stepped depicts whether the values are incremental or random. True is incremental.
    public static boolean stepped = false;

    public static void main(String[] args) {
        frame = new Frame();
        resetArray();
        frame.setLocationRelativeTo(null);
    }

    public static void resetArray(){
        // If we are currently running a sorting method, then isSorting should be true
        // and the array should not be reset while it is being sorted
        if (isSorting) { return; }
        toBeSorted = new Integer[toBeSortedSize];
        blockWidth = (int) Math.max(Math.floor(500/ toBeSortedSize), 1);
        for(int i = 0; i<toBeSorted.length; i++){
            toBeSorted[i] = (int) (toBeSortedSize *Math.random());
        }
        // If we're using incremental values, they are already sorted. This shuffles it.
        frame.preDrawArray(toBeSorted);
    }

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

                default:
                    isSorting = false;
                    return;
            }

            sortingThread.start();

        }
    }
}
