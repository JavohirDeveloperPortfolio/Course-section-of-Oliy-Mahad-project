package uz.oliymahad.courseservice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.oliymahad.courseservice.dto.ApiResponse;
import uz.oliymahad.courseservice.dto.FilterQueueForGroupsDTO;
import uz.oliymahad.courseservice.dto.group.GroupRequestDto;
import uz.oliymahad.courseservice.entity.BaseEntity;
import uz.oliymahad.courseservice.entity.course.CourseEntity;
import uz.oliymahad.courseservice.entity.group.GroupEntity;
import uz.oliymahad.courseservice.entity.group.GroupStatusEnum;
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


    public ApiResponse addGroup(GroupRequestDto groupRequestDto) {

        Optional<CourseEntity> courseEntity = courseRepository.findById(groupRequestDto.getCourseId());

        if (courseEntity.isEmpty()) {
            return new ApiResponse("course not found", true);
        }

        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setName(groupRequestDto.getName());
        groupEntity.setMembersCount(groupRequestDto.getMembersCount());
        groupEntity.setType(EGender.valueOf(groupRequestDto.getType()));
        groupEntity.setGroupStatus(GroupStatusEnum.UPCOMING);
        groupEntity.setStartDate(groupRequestDto.getStartDate());
        groupEntity.setCourse(courseEntity.get());

        GroupEntity groupEntityDB = groupRepository.save(groupEntity);

        FilterQueueForGroupsDTO filterQueueDTO = new FilterQueueForGroupsDTO();
        filterQueueDTO.setCourseId(groupRequestDto.getCourseId());
        filterQueueDTO.setGender(groupRequestDto.getType());
        filterQueueDTO.setLimit(groupRequestDto.getMembersCount());
        filterQueueDTO.setStatus(Status.ACCEPT.toString());
        ApiResponse<List<Long>> usersByFilter = queueService.getUsersByFilter(filterQueueDTO);

        List<GroupUsersEntity> groupUsers = new ArrayList<>();
        for (long userId : usersByFilter.getData()) {
            GroupUsersEntity groupUser = new GroupUsersEntity();
            groupUser.setUserId(userId);
            groupUser.setGroup(groupEntityDB);
            GroupUsersEntity groupUserDB = groupUsersRepository.save(groupUser);
            groupUsers.add(groupUserDB);
        }

        groupEntityDB.setGroupUsers(groupUsers);
        groupRepository.save(groupEntityDB);


        groupRepository.save(groupEntity);

        return new ApiResponse("group added \n", true);
    }

    public ApiResponse getAllGroups(){
        List<GroupEntity> allGroup = groupRepository.findAll();
        return new ApiResponse("All Groups", true, allGroup) ;
    }

    public ApiResponse getGroupById(Long id){
        Optional<GroupEntity> groupEntity = groupRepository.findById(id);
        return new ApiResponse("One Groups", true, groupEntity) ;
    }

    public ApiResponse deleteGroupById(Long id){
        groupRepository.deleteById(id);
        return new ApiResponse("group deleted", true) ;
    }

    public ApiResponse editGroupInfo(GroupRequestDto newGroup, Long id){
        GroupEntity oldGroup = groupRepository.getById(id);
        if (!newGroup.getName().isBlank()) oldGroup.setName(newGroup.getName());
        if (newGroup.getMembersCount() != 0) oldGroup.setMembersCount(newGroup.getMembersCount());
        if (!newGroup.getType().isBlank()) oldGroup.setType(EGender.valueOf(newGroup.getType()));
        if (newGroup.getStartDate() != null) oldGroup.setStartDate(newGroup.getStartDate());
        if (newGroup.getCourseId() != 0) {
            Optional<CourseEntity> courseEntity = courseRepository.findById(newGroup.getCourseId());
            oldGroup.setCourse(courseEntity.get());
        }
        groupRepository.save(oldGroup) ;
        return new ApiResponse("group updated", true);
    }



//    public ApiResponse addByOneById(){
//
//    }


}
