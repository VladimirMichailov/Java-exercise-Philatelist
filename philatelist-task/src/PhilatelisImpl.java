import lt.techin.philatelist.Philatelist;
import lt.techin.philatelist.PostStamp;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PhilatelisImpl implements Philatelist {

    private ArrayList<PostStamp> stamps = new ArrayList<>();

    @Override
    public void addToCollection(PostStamp postStamp) {
        if (postStamp == null) throw new IllegalArgumentException();
        if(postStamp.getName()==null) throw new IllegalArgumentException();
        if(postStamp.getName().isEmpty()) throw new IllegalArgumentException();
        stamps.add(postStamp);

    }

    @Override
    public int getNumberOfPostStampsInCollection() {

        return stamps.size();
        //   return (int) stamps.stream().count();
    }

    @Override
    public void printAllPostStampNames() {

        stamps.stream().map(PostStamp::getName).forEach(System.out::println);

//        for (int i = 0; i < stamps.size(); i++) {
//            System.out.println(stamps.get(i).getName());
//        }
    }

    @Override
    public void printPostStampsWithPriceGreaterThan(double v) {

        for (PostStamp stamp : stamps) {
            if (stamp.getMarketPrice() > v) {
                System.out.println(stamp.getName());

            }

        }

    }

    @Override
    public boolean isPostStampInCollection(PostStamp postStamp) {

        for (int i = 0; i < stamps.size(); i++) {
            if (stamps.contains(postStamp)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isPostStampWithNameInCollection(String s) {

        for (int i = 0; i < stamps.size(); i++){
            if (stamps.get(i).getName().equals(s)){
                return true;
            }
        }

        return false;
    }

    @Override
    public double calculateTotalMarketPrice() {
        double sum = 0;
        for (PostStamp stamp : stamps) {
            sum = sum + stamp.getMarketPrice();
        }
        return sum;
    }

    @Override
    public double getAveragePostStampPrice() {
        double sum = 0;
        for (PostStamp stamp : stamps) {
            sum = sum + stamp.getMarketPrice();

        }
        return sum/stamps.size();


    }

    @Override
    public PostStamp getTheMostExpensivePostStampByMarketValue() {

        PostStamp maxPrice = stamps.get(0);

        for (PostStamp stamp : stamps){
            if (stamp.getMarketPrice() > maxPrice.getMarketPrice()) {
                maxPrice = stamp;
            }
        }

        return maxPrice;
            }


    @Override
    public List<PostStamp> findPostStampsByNameContaining(String s) {
        return stamps.stream().filter(x->x.getName().contains(s)).collect(Collectors.toList());
    }

    @Override
    public List<PostStamp> getSortedPostStampsByName() {
        return stamps.stream().sorted(Comparator.comparing(PostStamp::getName)).collect(Collectors.toList());
    }
}
