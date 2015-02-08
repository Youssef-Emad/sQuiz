class CreatingQuizzesTest < ActionDispatch::IntegrationTest	
	include Devise::TestHelpers
	test 'creates quiz' do	
		instructor = Instructor.create(name:"Ahmed",email:"ahmed@example.com",password:"secret123")
		login_as(instructor)
		post "/api/quizzes",	
		{ quiz:	
		{ name: 'Quiz1', subject: 'physics', duration: 10, no_of_MCQ: 5, no_of_rearrangeQ: 5 }	
		}.to_json,	
		{ 'Accept' => Mime::JSON, 'Content-Type' => Mime::JSON.to_s }
		assert_equal 201, response.status	
		assert_equal Mime::JSON, response.content_type	
		quiz = json(response.body)	
	end	
	# test 'does not create quiz with name nil' do	
	# 	post "/api/quizzes",	
	# 	{ quiz:	
	# 	{ name: nil, subject: 'physics', duration: 10, no_of_MCQ: 5, no_of_rearrangeQ: 5 }	
	# 	}.to_json,	
	# 	{ 'Accept' => Mime::JSON, 'Content-Type' => Mime::JSON.to_s }
	# 	assert_equal 422, response.status	
	# 	assert_equal Mime::JSON, response.content_type		
	# end		
end
