package ua.nure.matchenko.practice6.part5;

public class Tree<E extends Comparable<E>> {
    private Node<E> rootNode;

    private static class Node<E> {
        private E item;
        private Node<E> leftNode;
        private Node<E> rightNode;

        public Node(E item) {
            this.item = item;
        }

        @Override
        public String toString() {
            return item.toString();
        }
    }

    public boolean add(E e) {
        Node<E> nextNode = rootNode;
        Node<E> currentNode = null;
        while (nextNode != null) {
            int compareValue = e.compareTo(nextNode.item);
            if (compareValue == 0) {
                return false;
            } else {
                currentNode = nextNode;
                if (compareValue < 0) {
                    nextNode = nextNode.leftNode;
                } else {
                    nextNode = nextNode.rightNode;
                }
            }
        }
        Node<E> node = new Node<>(e);
        if (currentNode == null) {
            rootNode = node;
        } else {
            if (e.compareTo(currentNode.item) < 0) {
                currentNode.leftNode = node;
            } else {
                currentNode.rightNode = node;
            }
        }
        return true;
    }

    public void addAll(E[] elements) {
        for (E e : elements) {
            add(e);
        }
    }

    public boolean remove(E e) {
        Node<E> nextNode = rootNode;
        Node<E> currentNode = null;
        while (nextNode != null) {
            int compareValue = e.compareTo(nextNode.item);
            if (compareValue == 0) {
                if (nextNode == rootNode) {
                    currentNode = rootNode;
                }
                break;
            } else {
                currentNode = nextNode;
                if (compareValue < 0) {
                    nextNode = nextNode.leftNode;
                } else {
                    nextNode = nextNode.rightNode;
                }
                if (nextNode == null) {
                    return false;
                }
            }
        }
        if (currentNode == null) {
            return false;
        }
        removeAccordingToTheNumberOfChildren(nextNode, currentNode);
        return true;
    }

    private void removeAccordingToTheNumberOfChildren(Node<E> nextNode, Node<E> currentNode) {
        int numberOfChildren = 2;
        Node<E> childNode = null;
        if (nextNode.leftNode == null) {
            numberOfChildren--;
            childNode = nextNode.rightNode;
        }
        if (nextNode.rightNode == null) {
            numberOfChildren--;
            childNode = nextNode.leftNode;
        }
        int compareValue = currentNode.item.compareTo(nextNode.item);
        if (numberOfChildren == 0) {
            if (compareValue < 0) {
                currentNode.rightNode = null;
            } else {
                currentNode.leftNode = null;
            }
        } else if (numberOfChildren == 1) {
            if (compareValue < 0) {
                currentNode.rightNode = childNode;
            } else {
                currentNode.leftNode = childNode;
            }
        } else {
            Node<E> lastLeftNode = nextNode.rightNode;
            Node<E> preLastLeftNode = nextNode;
            while (lastLeftNode.leftNode != null) {
                preLastLeftNode = lastLeftNode;
                lastLeftNode = lastLeftNode.leftNode;
            }
            lastLeftNode.leftNode = nextNode.leftNode;
            if (nextNode.rightNode != lastLeftNode) {
                lastLeftNode.rightNode = nextNode.rightNode;
            }
            if (compareValue < 0) {
                currentNode.rightNode = lastLeftNode;
            } else if (compareValue > 0){
                currentNode.leftNode = lastLeftNode;
            } else {
                rootNode = currentNode.rightNode;
            }
            preLastLeftNode.leftNode = null;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (rootNode != null) {
            leftNodeToString(builder);
            builder.append(rootNode);
            rightNodeToString(builder);
        }
        return builder.toString();
    }

    private void rightNodeToString(StringBuilder builder) {
        if (rootNode.rightNode != null) {
            if (rootNode.rightNode.leftNode != null) {
                if (rootNode.rightNode.leftNode.leftNode != null) {
                    builder.append("\n")
                            .append("      ")
                            .append(rootNode.rightNode.leftNode.leftNode);
                }
                builder.append("\n");
                builder.append("    ");
                builder.append(rootNode.rightNode.leftNode);

                if (rootNode.rightNode.leftNode.rightNode != null) {
                    builder.append("\n")
                            .append("      ")
                            .append(rootNode.rightNode.leftNode.rightNode);
                }
            }
            builder.append("\n");
            builder.append("  ")
                    .append(rootNode.rightNode);
            if (rootNode.rightNode.rightNode != null) {
                if (rootNode.rightNode.rightNode.leftNode != null) {
                    builder.append("\n")
                            .append("      ")
                            .append(rootNode.rightNode.rightNode.leftNode);
                }
                builder.append("\n")
                        .append("    ")
                        .append(rootNode.rightNode.rightNode);
                if (rootNode.rightNode.rightNode.rightNode != null) {
                    builder.append("\n")
                            .append("      ")
                            .append(rootNode.rightNode.rightNode.rightNode);
                }
            }
        }
    }

    private void leftNodeToString(StringBuilder builder) {
        if (rootNode.leftNode != null) {
            if (rootNode.leftNode.leftNode != null) {
                if (rootNode.leftNode.leftNode.leftNode != null) {
                            builder.append("      ")
                            .append(rootNode.leftNode.leftNode.leftNode)
                            .append("\n");
                }
                builder.append("    ")
                        .append(rootNode.leftNode.leftNode)
                        .append("\n");
                if (rootNode.leftNode.leftNode.rightNode != null) {
                            builder.append("      ")
                            .append(rootNode.leftNode.leftNode.rightNode)
                            .append("\n");
                }
            }
            builder.append("  ")
                    .append(rootNode.leftNode)
                    .append("\n");
            if (rootNode.leftNode.rightNode != null) {
                if (rootNode.leftNode.rightNode.leftNode != null) {
                            builder.append("      ")
                            .append(rootNode.leftNode.rightNode.leftNode)
                            .append("\n");
                }
                        builder.append("    ")
                                .append(rootNode.leftNode.rightNode)
                                .append("\n");
                if (rootNode.leftNode.rightNode.rightNode != null) {
                            builder.append("      ")
                            .append(rootNode.leftNode.rightNode.rightNode)
                            .append("\n");
                }
            }
        }
    }

    public void print() {
        System.out.println(toString());
    }

    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        //Integer[] integers = new Integer[] {8, 3, 20, 1, 6, 24, 5, 7, 23, 25, 15, 10, 18, 2, 0};
        Integer[] integers = new Integer[] {8, 3, 10, 1, 6, 14, 4, 7, 13};
        tree.addAll(integers);
        tree.print();
        System.out.println("~~~~~~~~~~~~~~~~");
        //tree.remove(20);
        tree.remove(3);
        tree.print();
    }
}
