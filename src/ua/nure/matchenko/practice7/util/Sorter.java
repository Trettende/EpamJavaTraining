package ua.nure.matchenko.practice7.util;

import org.xml.sax.SAXException;
import ua.nure.matchenko.practice7.constants.Constants;
import ua.nure.matchenko.practice7.controller.DOMController;
import ua.nure.matchenko.practice7.entity.Blade;
import ua.nure.matchenko.practice7.entity.Knife;
import ua.nure.matchenko.practice7.entity.SteelArm;
import ua.nure.matchenko.practice7.entity.Visual;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

public class Sorter {
    public static final Comparator<Knife> sortKnivesByTypeComparator =
            new Comparator<Knife>() {
                @Override
                public int compare(Knife o1, Knife o2) {
                    return o1.getType().compareTo(o2.getType());
                }
            };

    public static final Comparator<Knife> sortKnivesByVisualsNumberComparator =
            new Comparator<Knife>() {
                @Override
                public int compare(Knife o1, Knife o2) {
                    return o2.getVisuals().size() - o1.getVisuals().size();
                }
            };

    public static final Comparator<Visual> sortVisualsByBladeLengthComparator =
            new Comparator<Visual>() {
                @Override
                public int compare(Visual o1, Visual o2) {
                    return String.valueOf(o1.getBlade().getLength()).compareTo(
                            String.valueOf(o2.getBlade().getLength()));
                }
            };

    public static final Comparator<Knife> sortKnivesByValueComparator =
            new Comparator<Knife>() {
                @Override
                public int compare(Knife o1, Knife o2) {
                    if (o1.isValue() && !o2.isValue()) {
                        return -1;
                    }
                    if (o2.isValue() && !o1.isValue()) {
                        return 1;
                    }
                    return 0;
                }
            };

    public static final void sortKnivesByType(SteelArm steelArm) {
        Collections.sort(steelArm.getKnives(), sortKnivesByTypeComparator);
    }

    public static final void sortKnivesByVisualsNumber(SteelArm steelArm) {
        Collections.sort(steelArm.getKnives(), sortKnivesByVisualsNumberComparator);
    }

    public static final void sortVisualsByBladeLength(SteelArm steelArm) {
        for (Knife knife : steelArm.getKnives()) {
            /*for (Visual visual : knife.getVisuals()) {
                Collections.sort(visual.getBlade().getLength(), sortVisualsByBladeLength;
            }*/
            Collections.sort(knife.getVisuals(), sortVisualsByBladeLengthComparator);
        }
    }

    public static final void sortKnivesByValue(SteelArm steelArm) {
        Collections.sort(steelArm.getKnives(), sortKnivesByValueComparator);
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        DOMController domController = new DOMController(Constants.VALID_XML_FILE);
        domController.parse(false);
        SteelArm steelArm = domController.getSteelArm();

        System.out.println("====================================");
        System.out.println(steelArm);
        System.out.println("====================================");

        System.out.println("====================================");
        Sorter.sortKnivesByType(steelArm);
        System.out.println(steelArm);
        System.out.println("====================================");

        System.out.println("====================================");
        Sorter.sortKnivesByVisualsNumber(steelArm);
        System.out.println(steelArm);
        System.out.println("====================================");

        System.out.println("====================================");
        Sorter.sortVisualsByBladeLength(steelArm);
        System.out.println(steelArm);
        System.out.println("====================================");

        System.out.println("====================================");
        Sorter.sortKnivesByValue(steelArm);
        System.out.println(steelArm);
        System.out.println("====================================");
    }


}
