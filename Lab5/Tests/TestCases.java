import java.util.*;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class TestCases
{

   private static final Song[] songs = new Song[] {
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Gerry Rafferty", "Baker Street", 1978)
      };

   @Test
   public void testArtistComparator() {
       Comparator<Song> myCompare = new ArtistComparator();
      assertEquals(myCompare.compare(songs[0],songs[1]),-14); //Negative number
       Comparator<Song> myCompare2 = new ArtistComparator();
       assertEquals(myCompare2.compare(songs[5],songs[7]),-1); //Negative number
       Comparator<Song> myCompare3 = new ArtistComparator();
       assertEquals(myCompare3.compare(songs[0],songs[2]),3); //Negative number
   }


   @Test
   public void testLambdaTitleComparator() {
        Comparator<Song> lambdaComp = (Song o1, Song o2) -> o1.getTitle().compareTo(o2.getTitle());
        assertEquals(lambdaComp.compare(songs[0],songs[1]),8);
       assertEquals(lambdaComp.compare(songs[4],songs[6]),17);
       assertEquals(lambdaComp.compare(songs[3],songs[2]),-18);
   }

   @Test
   public void testYearExtractorComparator() {
       List<Song> songs2 = new ArrayList<>();
       songs2.add(new Song("Decemberists", "The Mariner's Revenge Song", 2005));
       songs2.add(new Song("Rogue Wave", "Love's Lost Guarantee", 2005));
       songs2.add(new Song("Avett Brothers", "Talk on Indolence", 2006));
       songs2.add(new Song("Queen", "Bohemian Rhapsody", 1975));
       songs2.add(new Song("Gerry Rafferty", "Baker Street", 1998));

       Comparator<Song> comp = Comparator.comparing(Song::getYear);
       Collections.sort(songs2, comp.reversed());

       //System.out.println(songs2);
       assertEquals(comp.compare(songs2.get(0),songs2.get(1)),1);
       assertEquals(comp.compare(songs2.get(2),songs2.get(3)),1);

   }

   @Test
   public void testComposedComparator() {
       Comparator<Song> comp = Comparator.comparing(Song :: getArtist);
       Comparator<Song> comp2 = Comparator.comparing(Song :: getYear);
       Comparator<Song> composedComp = new ComposedComparator(comp,comp2);
       assertEquals(composedComp.compare(songs[3],songs[7]),1);
       assertEquals(composedComp.compare(songs[2],songs[5]),-5);
       assertEquals(composedComp.compare(songs[7],songs[1]),-11);

   }

   @Test
   public void testThenCompaing() {
       Comparator<Song> comp1 = Comparator.comparing(Song :: getTitle);
       Comparator<Song> thenCompare = comp1.thenComparing(Comparator.comparing(Song :: getArtist));
       assertEquals(thenCompare.compare(songs[3],songs[5]),1);
       assertEquals(thenCompare.compare(songs[1],songs[5]),10);
       assertEquals(thenCompare.compare(songs[3],songs[2]),-18);

   }

   @Test
   public void runSort()
   {
       Comparator<Song> sortComp = Comparator.comparing(Song :: getArtist)
               .thenComparing(Comparator.comparing(Song :: getTitle))
               .thenComparing(Comparator.comparing(Song :: getYear));

      List<Song> songList = new ArrayList<>(Arrays.asList(songs));
      List<Song> expectedList = Arrays.asList(
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Gerry Rafferty", "Baker Street", 1978),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005)
         );

      songList.sort(sortComp);
      assertEquals(songList, expectedList);
   }
}
