package tester;

import maps.TreeMapBST;
import maps.TreeMapRBTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TestTreeMapRBTree {
    public static void main(String[] args) {
        TreeMapRBTree<Integer,String> T1 = new TreeMapRBTree<>();

        T1.turnOnCaseTracing();

        T1.put(41, "Eric");
        T1.put(38, "Pat");
        T1.put(31, "Caroline");
        T1.put(12, "Tom");
        T1.put(19, "Ann");
        T1.put(8, "Jennifer");

        T1.clear() ;
    }
}
