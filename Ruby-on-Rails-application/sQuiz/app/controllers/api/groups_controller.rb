class Api::GroupsController < ApplicationController
  acts_as_token_authentication_handler_for Instructor

  respond_to :json

  def create

    if instructor_signed_in?
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
    else
      render json: {
      error: "You must be an instructor to create a group",
      status: 400
      } ,  status: 400
    end                   
  end


  def destroy
    group = Group.find(params[:group][:id])
    if(group!=nil)
      if (current_instructor == group.instructor)
        group.destroy 
        render status: 200,
        json: { success: true,
        info: "Group Destroyed"
        }
      else
        render json: {info: "You must be the group's instructor to destroy it",
        status: 400
        } , status: 400
      end
    else
      render json: {error: "Group is not found",
      status: 400
      } , status: 400
     end      
  end

  private
  def group_params
      params.require(:group).permit(:name)
  end	
end


