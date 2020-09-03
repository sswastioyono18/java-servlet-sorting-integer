import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import org.apache.commons.lang3.StringUtils;

@WebServlet("/InputServlet")
public class InputServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {


        String input = request.getParameter("input");
        String[] stringOfArrayInput = input.split(",");
        Integer[] arrayOfInteger = new Integer[stringOfArrayInput.length];

        for(int i = 0; i < stringOfArrayInput.length; i++) {
            try{

                arrayOfInteger[i] = Integer.parseInt(stringOfArrayInput[i]);
            } catch (Exception e){
                System.out.println("NOT A NUMBER");
            }
        }

        Queue<Integer> topNthHighest = calculateTopNthHighest(arrayOfInteger);

        int nthHighest = 5;
        String htmlRespone = "Top " +nthHighest+ " highest integer(s) are : " + printPriorityQueue(topNthHighest, nthHighest);

        response.getWriter().write(htmlRespone);

    }

    private String printPriorityQueue(Queue<Integer> topNthhighest, int nthHighest){

        Integer val;
        List<Integer> resultList = new ArrayList<>();
        while( (val = topNthhighest.poll()) != null && nthHighest > 0) {
            resultList.add(val);
            nthHighest--;
        }
        return StringUtils.join(resultList, ",");
    }

    private Queue<Integer> calculateTopNthHighest(Integer[] arrayOfInteger) {
        Queue<Integer> topNthHighest = new PriorityQueue<>((x, y) -> Integer.compare(y, x));
        topNthHighest.addAll(Arrays.asList(arrayOfInteger));

        return topNthHighest;
    }

}