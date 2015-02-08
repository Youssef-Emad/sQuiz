FactoryGirl.define do
  factory :instructor ,class: Student do
    name'instructor' 
    email 'example@eng.asu.edu.eg' 
    password 'abc12345678'
  end
  factory :instructor1 ,class: Student do
    name'instructor1' 
    email 'example1@eng.asu.edu.eg' 
    password 'abc12345678'
  end
end
