package phasethree;

import java.util.*;

public class BipartiteMatching {

    // M is number of applicants
    // and N is number of hospitals
    static final int M = 6;

    static final int N = 6;

    static int matchR[] = new int[N];

    // A DFS based recursive function that
    // returns true if a matching for
    // vertex u is possible
    boolean bpm(boolean bpGraph[][], int u,
            boolean seen[], int matchR[]) {

        // Try every hospital one by one
        for (int v = 0; v < N; v++) {

            // If applicant u is interested
            // in hospital v and v is not visited
            if (bpGraph[u][v] && !seen[v]) {

                // Mark v as visited
                seen[v] = true;

                // If hospital 'v' is not assigned to
                // an applicant OR previously
                // assigned applicant for hospital v (which
                // is matchR[v]) has an alternate hospital available.
                // Since v is marked as visited in the
                // above line, matchR[v] in the following
                // recursive call will not get hospital 'v' again
                if (matchR[v] < 0 || bpm(bpGraph, matchR[v],
                        seen, matchR)) {

                    matchR[v] = u;

                    return true;

                }

            }

        }

        return false;

    }

    // Returns maximum number
    // of matching from M to N
    int maxBPM(boolean bpGraph[][]) {

        // An array to keep track of the
        // applicants assigned to hospitals.
        // The value of matchR[i] is the
        // applicant number assigned to hospital i,
        // the value -1 indicates nobody is assigned.
        // int matchR[] = new int[N];
        // Initially all hospitals are available
        for (int i = 0; i < N; ++i) {

            matchR[i] = -1;

        }

        // Count of hospitals assigned to applicants
        int result = 0;

        for (int u = 0; u < M; u++) {

            // Mark all hospitals as not seen
            // for next applicant.
            boolean seen[] = new boolean[N];

            for (int i = 0; i < N; ++i) {

                seen[i] = false;

            }

            // Find if the applicant 'u' can get a hospital
            if (bpm(bpGraph, u, seen, matchR)) {

                result++;

            }

        }

        return result;

    }

    // Driver Code
    public static void main(String[] args)
            throws java.lang.Exception {

        // Let us create a bpGraph shown
        // in the above example
        boolean bpGraph[][] = new boolean[][]{
            {true, true, false,
                false, false, false},
            {false, false, false,
                false, false, true},
            {true, false, false,
                true, false, false},
            {false, false, true,
                false, false, false},
            {false, false, false,
                true, true, false},
            {false, false, false,
                false, false, true}};

        BipartiteMatching m = new BipartiteMatching();

        int ans = m.maxBPM(bpGraph);

        for (int i = 0; i < N; i++) {

            if (matchR[i] > -1) {

                switch (matchR[i] + 1) {
                    case 1: {
                        System.out.print("Ahmed");
                        break;
                    }
                    case 2: {
                        System.out.print("Mahmoud");
                        break;
                    }
                    case 3: {
                        System.out.print("Eman");
                        break;
                    }
                    case 4: {
                        System.out.print("Fatimah");
                        break;
                    }
                    case 5: {
                        System.out.print("Kamel");
                        break;
                    }
                    case 6: {
                        System.out.print("Nojood");
                        break;
                    }

                }

                //System.out.println((i + 1) + "th hospital has patient number " + (matchR[i] + 1));
                System.out.print(" ---> ");
                switch (i + 1) {
                    case 1: {
                        System.out.print("King Abdulaziz University");
                        break;
                    }
                    case 2: {
                        System.out.print("King Fahd");
                        break;
                    }
                    case 3: {
                        System.out.print("East Jeddah");
                        break;
                    }
                    case 4: {
                        System.out.print("King Fahad Armed Forces");
                        break;
                    }
                    case 5: {
                        System.out.print("King Faisal Speacilist");
                        break;
                    }
                    case 6: {
                        System.out.print("Ministry of National Guard");
                        break;
                    }

                }

                System.out.println("");

            } else {

           }

        }

        System.out.println("\nMaximum number of applicants that can"
                + " get a position in hospital = " + ans);

    }
}
