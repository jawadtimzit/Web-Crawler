package webCrawlerPackage;


	

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WebCrawler {

    /**
     * declaring queue which will store the URLs
     */

    public static Queue<String> q = new LinkedList<>();
    //Store visited URL
    public static Set<String> marked = new HashSet<>();
    //Extract the html Content
    public static String regex = "http[s]*://(\\w+\\.)*(\\w+)";

    /**
     * Declaring the root URL and the number of hops
     */

    public static String root;
    public static int hops;
    public static int var = 1;

    /**
     * does a breadth first search on the URl
     * and crawls all the URl
     * @throws IOException
     */
    public static void bfs() throws IOException{
        /**
         * Add the root URL to the queue
         */
        q.add(root);
        /**
         * Run the loop while queue is not empty
         */
        while(!q.isEmpty()){
            String s = q.poll();
            if(marked.size()>hops)return;
            boolean ok = false;
            URL url = null;
            BufferedReader br = null;
            while(!ok){
                try{
                    /**
                     * Hitting the URL
                     * using the URL class in java
                     */
                    url = new URL(s);
                    br = new BufferedReader(new InputStreamReader(url.openStream()));
                    ok = true;
                    //Malformed URL or wrong url from Clinet
                } catch(MalformedURLException e){
                    System.out.println("400 Response Encountered with this URL " + s);
                    System.out.println("Skipping the URL "+ s);
                    s = q.poll();
                    ok = false;
                } catch(IOException e){
                   // retry to reload the page 3 times
                	System.out.println("500 Response Encountered with this URL " + s);
                    int retryCount = 3;
                    while(retryCount > 0) {
                        System.out.println("Retrying three times with URL " + s);
                        q.add(s);
                        retryCount--;
                    }
                    s = q.poll();
                    ok = false;
                } catch(Exception e) {
                    System.out.println("300 Response Encountered with this URL " + s);
                    System.out.println("Redirecting to http://www.google.com/history/optout?hl=en");
                    root = "http://www.google.com/history/optout?hl=en";
                    bfs();
                    s = q.poll();
                    ok = false;
                }
            }

            /**
             * using stringbuilder to store the resulting string
             */
            StringBuilder sb = new StringBuilder();

            /**
             * reading the page html
             */
            while((s = br.readLine())!=null){
                sb.append(s);
            }
            s = sb.toString();

            /**
             * Matching against the REGEX if it is a url or not
             */

            Pattern pattern = Pattern.compile(regex);

            /**
             * Matching the pattern against the URL
             */

            Matcher matcher = pattern.matcher(s);

            while(matcher.find()){
                String href = matcher.group();

                if(!marked.contains(href) && marked.size() < hops){
                    marked.add(href);
                    System.out.println(var + " Hop to:"+ href);
                    if (var == hops) {
                        br = new BufferedReader(new InputStreamReader(url.openStream()));
                        while ((s=br.readLine())!=null)
                        {

                            System.out.println(s);
                        }
                    }
                    if (var == hops) return ;
                    var++;
                    q.add(href);
                }
            }
        }
        System.out.println();
    }
    
    
    /**
     * Main method the entry point of the code
     * BFS is called from here
     * @param args
     */
    public static void main(String[] args){
        root = args[0];
        hops = Integer.parseInt(args[1]);
        System.out.println();
        System.out.println("Going to " + root);
        System.out.println();
        System.out.println("The number of hops from that URL :"+hops);
        try{
            bfs();
        }catch(IOException e){
            System.out.println("IOException caught : "+e);
        }
    }
}






