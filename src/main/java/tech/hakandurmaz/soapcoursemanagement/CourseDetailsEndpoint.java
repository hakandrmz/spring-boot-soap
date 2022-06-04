package tech.hakandurmaz.soapcoursemanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import tech.hakandurmaz.courses.*;

import java.math.BigInteger;
import java.util.List;

@Endpoint
public class CourseDetailsEndpoint {

    @Autowired
    CourseDetailsService service;

    @PayloadRoot(namespace = "http://hakandurmaz.tech/courses", localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetails(@RequestPayload GetCourseDetailsRequest request) {
        Course course = service.findById(request.getId().intValue());
        if(course==null) {
            throw new CourseNotFoundException("Course not found");
        }
        return mapCourseDetails(course);
    }

    @PayloadRoot(namespace = "http://hakandurmaz.tech/courses", localPart = "GetAllCourseDetailsRequest")
    @ResponsePayload
    public GetAllCourseDetailsResponse processAllCourseDetails(@RequestPayload GetAllCourseDetailsRequest request) {

        List<Course> courses = service.getAll();

        return mapAllCourseDetails(courses);
    }

    @PayloadRoot(namespace = "http://hakandurmaz.tech/courses", localPart = "DeleteCourseDetailsRequest")
    @ResponsePayload
    public DeleteCourseDetailsResponse deleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) {

        CourseDetailsService.Status status = service.deleteById(request.getId().intValue());

        DeleteCourseDetailsResponse deleteCourseDetailsResponse = new DeleteCourseDetailsResponse();
        deleteCourseDetailsResponse.setStatus(mapStatus(status));

        return deleteCourseDetailsResponse;
    }

    private Status mapStatus(CourseDetailsService.Status status) {
        if(status== CourseDetailsService.Status.FAILURE) {
            return Status.FAILURE;
        }
        else {
            return Status.SUCCESS;
        }
    }

    private GetCourseDetailsResponse mapCourseDetails(Course course) {
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        response.setCourseDetails(mapCourse(course));
        return response;
    }

    private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
        GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
        for (Course course : courses) {
            CourseDetails mapCourse = mapCourse(course);
            response.getCourseDetails().add(mapCourse);
        }
        return response;
    }

    private CourseDetails mapCourse(Course course) {
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(BigInteger.valueOf(course.getId()));
        courseDetails.setName(course.getName());
        courseDetails.setDescription(course.getDescription());
        return courseDetails;
    }

}
