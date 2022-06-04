package tech.hakandurmaz.soapcoursemanagement;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CourseDetailsService {

    private static List<Course> courseList = new ArrayList<>();

    static {
        courseList.add(new Course(1,"Mat","mat desc"));
        courseList.add(new Course(2,"fen","fen desc"));
        courseList.add(new Course(3,"turkce","turkce desc"));
    }

    Course findById(int id) {
        for(Course course : courseList) {
            if (course.getId() == id) {
                return course;
            }
        }
        return null;
    }

    List<Course> getAll() {
        return courseList;
    }

    public int deleteById(int id) {

        Iterator<Course> iterator = courseList.iterator();

        while (iterator.hasNext()) {
            Course course = iterator.next();
            if(course.getId()==id) {
                iterator.remove();
                return 1;
            }
        }
        return 0;
    }

    //course - 1
    //courses
    //delete
    //findbyid



}
