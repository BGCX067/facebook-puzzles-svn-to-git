package com.sholmes.facebook.smallworld;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * Facebook puzzle question smallworld.
 * Builds an output file with each users 3 closest friends listed in order.
 *
 * Requires input file passed at commandline in the
 * following format: userid latitude longitude
 *
 *
 * @author Justin A. McCoy <a
 *         href="mailto:justinmccoy@acm.org">justinmccoy@acm.org</a>
 */
public final class SmallWorld {
    private static final Integer NUMBER_OF_NEIGHBORS = 3;
    private static final Integer EARTH_RADIUS = 6371;
    /**
     * private because we are Utility.
     */
    private SmallWorld() {
    }

    /**
     * @param args filename of input file required for smallworld
     */
    public static void main(final String[] args) {

        Collection<User> users;
        try {
            users = buildUserStructure(args[0]);
        } catch (IOException ex) {
            System.out.println("Error reading from file: " + args[0]);
            return;
        }

        //Runtime O(n^2)
        for (User user : users) {
            printNearistNeighbors(user,
                    findNearistNeighbors(user, users, NUMBER_OF_NEIGHBORS));
        }
    }

    /**
     * @param aUser user we are printing out results for
     * @param nearistNeighbors who is closest to aUser
     */
    private static void printNearistNeighbors(final User aUser,
            final Collection<User> nearistNeighbors) {
        boolean flag = false;
        System.out.print(aUser.getId() + " ");
        for (User user : nearistNeighbors) {
            if (!flag) {
                flag = true;
            } else {
                System.out.print(",");
            }
            System.out.print(user.getId());
        }
        System.out.print("\n");
    }

    /**
     * O(n) + aNumberOfNeighbors.
     * @param aUser User to calculate all distances from
     * @param users All potential users to calculate distance to aUser
     * @param aNumberOfNeighbors as
     * @return Collection with the aNumberOfNeighbors that are closest to aUser
     */
    private static Collection<User> findNearistNeighbors(final User aUser,
            final Collection<User> users, final Integer aNumberOfNeighbors) {

        SortedMap<Integer, User> distanceCalcOfUsers
        = new TreeMap<Integer, User>();

        // Create sorted list of all users in respect to distance
        for (User user : users) {
            if (user.getId() != aUser.getId()) {     //Don't calculate for self
                distanceCalcOfUsers.put(calculateDistBtwn(aUser, user), user);
            }
        }

        Collection<User> neighbors = new ArrayList<User>();
        Iterator iter = distanceCalcOfUsers.entrySet().iterator();
        for (int inc = 0; (inc < aNumberOfNeighbors) && iter.hasNext(); inc++) {
            Entry<Integer, User> entry = (Entry<Integer, User>) iter.next();
            neighbors.add(entry.getValue());
        }
        return neighbors;
    }

    /**
     * @param aFile File to read user locations from. Expected format: id
     *            latitude longitude
     * @return a collection of users
     * @throws IOException error opening or reading file
     */
    private static Collection<User> buildUserStructure(final String aFile)
        throws IOException {
        Collection<User> users = new ArrayList<User>();

        final BufferedReader br = new BufferedReader(new FileReader(aFile));
        String data;
        User user = null;
        while ((data = br.readLine()) != null) {
            StringTokenizer tok = new StringTokenizer(data);
            user = new User(Integer.valueOf(tok.nextToken()),
                    Double.valueOf(tok.nextToken()),
                    Double.valueOf(tok.nextToken()));
            users.add(user);
        }
        br.close();
        return users;
    }

    /**
     * @param aUser1 user1 used in distance calculation
     * @param aUser2 user2 used in distance calculation
     * @return distance between aUser1 and aUser2 using the Spherical Law
     *         of Cosines formula.
     */
    private static Integer calculateDistBtwn(final User aUser1,
            final User aUser2) {

         Double distance = Math.acos(Math.sin(
                     Math.toRadians(aUser1.getLatitude()))
                 * Math.sin(Math.toRadians(aUser2.getLatitude()))
                 + Math.cos(Math.toRadians(aUser1.getLatitude()))
                 * Math.cos(Math.toRadians(aUser2.getLatitude()))
                 * Math.cos(Math.toRadians(aUser2.getLongitude()
                         - aUser1.getLongitude())))
                 * EARTH_RADIUS;

         return distance.intValue();
    }
}
