class Api::GroupsController < ApplicationController
  
  acts_as_token_authentication_handler_for Instructor

  def add

    student = Student.find_by_id(params[:student][:id])
    group = Group.find_by_id(params[:group][:id])

    if(!student)
      render status: :unprocessable_entity,
             json: { success: false,
                        info: "Student does not exit",
                        data: {} }

    elsif(!group)
      render status: :unprocessable_entity,
             json: { success: false,
                        info: "Group does not exit",
                        data: {} }  

    elsif (group.students.include?(student))
      render status: :unprocessable_entity,
             json: { success: false,
                        info: "Student already exists in this group",
                        data: {} }  
        
    elsif(group.instructor == current_instructor)

      group.students << student
      render status: 200,
              json: { success: true,
                      info: "Added",
                      data: { instructor: current_instructor,
                              student: student , group: group} }
    else
      render status: :unprocessable_entity,
             json: { success: false,
                        info: "Instructor is not authorized to add students to this group",
                        data: {} }

    end                    

  end

  def remove

    student = Student.find(params[:student][:id])
    group = Group.find(params[:group][:id])

    if(group.instructor == current_instructor)

      group.students.delete(student)
      render status: 200,
              json: { success: true,
                      info: "Removed",
                      data: { instructor: current_instructor,
                              student: student , group: group} }
    else
      render status: :unprocessable_entity,
             json: { success: false,
                        info: "Instructor is not authorized to remove students to this group",
                        data: {} }

    end                    

  end



  def create

  if instructor_signed_in?
    
   if(Group.find_by_name(params[:group][:name])==nil) 
    my_create_group_function
   else
       if(Group.find_by_name(params[:group][:name]).instructor != current_instructor )
            
       my_create_group_function 

       else

       render status: 400,
            json: { success: false,
                    info: "You Can't make another group with the same name ",
                   }

       end 
    end   

  else

render json: {
       error: "You must be an instructor to create a group",
       status: 400
  } ,  status: 400
     
  end                   

end


def destroy

    group = Group.find_by_name(params[:group][:name])
    if(group!=nil)
      if (current_instructor == group.instructor)
        group.destroy 
        render status: 200,
               json: { success: true,
                       info: "Group Destroyed"
                      }


      else
     
       render json: {error: "You must be the group's instructor to destroy it",
                     status: 400
                    } , status: 400


      end

    else

        render json: {error: "Group not found",
                     status: 400
                     } , status: 400




    end      
end

private

def group_params
    params.require(:group).permit(:name)
end 

def my_create_group_function

            group = Group.create(group_params)
            group.instructor = current_instructor
            if group.save

           render status: 200,
                  json: { success: true,
                    info: "Group Created",
                    data: { :group => group }}
            else

            render status: :unprocessable_entity,
                   json: { success: false,
                     info: group.errors,
                     data: {} }
            end

end  





end
