import java.util.List;
import java.util.Objects;

class Student
{
   private final String surname;
   private final String givenName;
   private final int age;
   private final List<CourseSection> currentCourses;

   public Student(final String surname, final String givenName, final int age,
      final List<CourseSection> currentCourses)
   {
      this.surname = surname;
      this.givenName = givenName;
      this.age = age;
      this.currentCourses = currentCourses;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Student student = (Student) o;

      if (age != student.age) return false;
      if (surname != null ? !surname.equals(student.surname) : student.surname != null) return false;
      if (givenName != null ? !givenName.equals(student.givenName) : student.givenName != null) return false;
      return currentCourses != null ? currentCourses.equals(student.currentCourses) : student.currentCourses == null;
   }

   @Override
   public int hashCode() {
      return Objects.hash(currentCourses, surname, givenName, age);
   }
}
