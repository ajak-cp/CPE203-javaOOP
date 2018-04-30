import java.time.LocalTime;
import java.util.Comparator;

class CourseSection
{
   private final String prefix;
   private final String number;
   private final int enrollment;
   private final LocalTime startTime;
   private final LocalTime endTime;

   public CourseSection(final String prefix, final String number,
      final int enrollment, final LocalTime startTime, final LocalTime endTime)
   {
      this.prefix = prefix;
      this.number = number;
      this.enrollment = enrollment;
      this.startTime = startTime;
      this.endTime = endTime;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      CourseSection that = (CourseSection) o;

      if (enrollment != that.enrollment) {return false;}
      if (prefix != null ? !prefix.equals(that.prefix) : that.prefix != null) return false;
      if (number != null ? !number.equals(that.number) : that.number != null) return false;
      if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
      return endTime != null ? endTime.equals(that.endTime) : that.endTime == null;
   }

   @Override
   public int hashCode() {
      int result = prefix != null ? prefix.hashCode() : 0;
      result = 31 * result + (number != null ? number.hashCode() : 0);
      result = 31 * result + enrollment;
      result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
      result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
      return result;
   }

   // additional likely methods not defined since they are not needed for testing
}
