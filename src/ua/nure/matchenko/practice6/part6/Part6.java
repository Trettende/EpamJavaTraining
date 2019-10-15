package ua.nure.matchenko.practice6.part6;

public class Part6 {
    public static void main(String[] args) {
        String path = null;
        String task = null;
        //String[] input = args[0].split(" ");
        for (int i = 0; i < 4; i+=2) {
            if ("--input".equals(args[i]) || "-i".equals(args[i])) {
                path = args[i+1];
            } else if ("--task".equals(args[i]) || "-t".equals(args[i])) {
                task = args[i+1];
            } else {
                throw new IllegalArgumentException();
            }
        }
        switch (task) {
            case "frequency":
                Part61.main(new String[] {path});
                break;
            case "length":
                Part62.main(new String[] {path});
                break;
            case "duplicates":
                Part63.main(new String[] {path});
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
}
