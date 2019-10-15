package ua.nure.matchenko.practice6.part5;

public class Part5 {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree();
        tree.add(3);
        System.out.println(tree.add(1));
        System.out.println(tree.add(1));
        Integer[] integers = new Integer[] {5, 0, 2, 4, 6};
        tree.addAll(integers);
        System.out.println("~~~~~~~");
        System.out.println(tree);
        System.out.println("~~~~~~~");
        System.out.println(tree.remove(5));
        System.out.println(tree.remove(5));
        System.out.println("~~~~~~~");
        System.out.println(tree);
    }
}
