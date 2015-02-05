class Api::GroupsController < ApplicationController

respond_to :json

def Create

  if instructor_signed_in?
	
	 group = Group.Create(Group_params)
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

  else

render json: {
       error: "You must be an instructor to create a group",
       status: 400
  } ,  status: 400
     
  end                   

end


def Destroy

  
    group = Group.find(params[:id])
    if current_instructor == group.instructor
    group.destroy 
    render status: 200,
           json: { success: true,
                   info: "Group Destroyed"
                      }


     else
     
 render json: {
    error: "You must be an instructor to Destroy a group",
    status: 400
  } , status: 400





end
end



def Group_params
    params.require(:group).permit(:name)
  end	



end	