package uz.oliymahad.courseservice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.oliymahad.courseservice.dto.ApiResponse;
import uz.oliymahad.courseservice.dto.FilterQueueDTO;
import uz.oliymahad.courseservice.dto.group.GroupRequestDto;
import uz.oliymahad.courseservice.entity.course.CourseEntity;
import uz.oliymahad.courseservice.entity.group.GroupEntity;
import uz.oliymahad.courseservice.entity.group.GroupUsersEntity;
import uz.oliymahad.courseservice.entity.quequeue.EGender;
import uz.oliymahad.courseservice.entity.quequeue.Status;
import uz.oliymahad.courseservice.repository.CourseRepository;
import uz.oliymahad.courseservice.repository.group.GroupRepository;
import uz.oliymahad.courseservice.repository.group.GroupUsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    private final CourseRepository courseRepository;

    private final QueueService queueService;

    private final GroupUsersRepository groupUsersRepository;


    public ApiResponse add(GroupRequestDto groupRequestDto) {

        Optional<CourseEntity> courseEntity = courseRepository.findById(groupRequestDto.getCourseId());

        if (courseEntity.isEmpty()) {
            return new ApiResponse("course not found", true);
        }

        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setName(groupRequestDto.getName());
        groupEntity.setType(EGender.valueOf(groupRequestDto.getType()));
        groupEntity.setMembersCount(groupRequestDto.getMembersCount());
        groupEntity.setCourseId(courseEntity.get());
        GroupEntity groupEntityDB = groupRepository.save(groupEntity);

        FilterQueueDTO filterQueueDTO = new FilterQueueDTO();
        filterQueueDTO.setCourseId(groupRequestDto.getCourseId());
        filterQueueDTO.setGender(groupRequestDto.getType());
        filterQueueDTO.setLimit(groupRequestDto.getMembersCount());
        filterQueueDTO.setStatus(Status.ACCEPT.toString());
        ApiResponse<List<Long>> usersByFilter = queueService.getUsersByFilter(filterQueueDTO);

        List<GroupUsersEntity> groupUsers = new ArrayList<>();
        for (long userId : usersByFilter.getData()) {
            GroupUsersEntity groupUser = new GroupUsersEntity();
            groupUser.setUserId(userId);
            groupUser.setGroupId(groupEntityDB);
            GroupUsersEntity groupUserDB = groupUsersRepository.save(groupUser);
            groupUsers.add(groupUserDB);
        }

        groupEntityDB.setGroupUsers(groupUsers);
        groupRepository.save(groupEntityDB);


        groupRepository.save(groupEntity);

        return new ApiResponse("group added \n", true);
    }


}
