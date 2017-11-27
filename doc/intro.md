#Introduction to the code base.

#build Result Tree
When called on a properly formatted set of subjects, returns a map with language non-specific question number as keys. Each value is a map with language code as keys, and each value being the processed result of that language and question, across all subjects.

#Fetching data
to get an individual question results, look it up in the data base.
'(get-in [:subject-number :question-number] subjects)'

to get an individual subject, also looking it up in the database is best. This is also how one would get a list of questions by subject.
'(:subject-number subjects)'

to get a question in aggregate, IE all results for that question, 
