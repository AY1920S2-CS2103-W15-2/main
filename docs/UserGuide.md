## 1. Introduction
HireLah! is targeted to bring greater convenience to interviewers who can type quickly and prefer typing over mouse commands. Additionally, we included customised features in our desktop apps to help interviewers to manage interviews.


## 2. Quick Start
Ensure you have Java 11 or above installed in your Computer.
Download the latest HireLah.jar somewhere.
Copy the file to the folder you want to use as the home folder for your HireLah!.
Double-click the file to start the app. The GUI should appear in a few seconds.


## 3. Features

### 3.0 Start Session
You can select, view, or delete interview sessions from a list of existing interview sessions to continue from an interview you left off previously, or you can create a new interview session from scratch.

**Command**
```
view session <session>
delete session <session>
start session <session>
new session <session>
```
**Example**
```
view session 
new session CEOInterview
```

_Action:_ views existing interview sessions, followed by creating and starting a new interview session named “CEOInterview”

_Output:_  
```
You have 1 existing interview session:  
JanitorInterview

Started new interview session: CEOInterview
```

### 3.1 Initialization Phase


#### 3.1.1 Add and Delete Interviewees
You can add more interviewees to the list so that you can keep their details easily. You can also delete the interviewees, who may have withdrawn their application by referring to either their full name, aliases, or assigned ID.

**Command**
```
new interviewee <fullname> [-a <alias>]
delete interviewee <interviewee>
list interviewee
```
**Execution Example**
```
> new interviewee Jane Doe -a Doe 
> list interviewee 
> delete interviewee Doe
```
_Action:_ Interviewee Jane Doe with alias of Doe is first created and then deleted via her alias

_Output:_
```
The following interviewee has been added:
  1. Jane Doe (alias: Doe)

These are the current interviewees:
  1. Jane Doe (alias: Doe)

The following interviewee has been deleted:
  1. Jane Doe (alias: Doe)
```

#### 3.1.2 Add, Update and Delete Attributes

**Command**
```
> new attribute <attribute> 
> update attribute <attribute> <new attribute>
> delete attribute <attribute> 
> list attribute
```
**Execution Example**
```
> new attribute teamwork
> new attribute leadership
> update attribute leadership leadership
> list attribute
> delete attribute teamwork
```
_Action:_ teamwork and leadership is first added. Leadership is updated to leadership, and then teamwork is deleted.

_Output:_
```
Attribute “teamwork” added

Attribute “leadership” added

Attribute “leadership” changed to “leadership”

These are the current attributes:
  1. teamwork
  2. leadership 

Attribute “teamwork” removed. You have now 1 attribute for your interview.
```

#### 3.1.3 Add, Update and Delete Interview Questions
Before the interview process, the interviewer may one to review the questions. During review process, the interviewer may wants to add more questions, remove the unnecessary questions and to edit the existing questions.

**Command**
```
new question <question>
update question <question number> <updated question>
list question
delete question <question number>
```
**Example**
```
> new question What are your relevant experiences?
> update question 1 What do you hope to accomplish in 5 years? 
> update question 2 How can you add value to the company?
> list question
> delete question 1
```
_Output:_ 
```
The following question has been added: 
  2. What are your relevant experiences?

Question 1 updated

Question 2 updated

These are the current questions:
  1. What do you hope to accomplish in 5 years? 
  2. How can you add value to the company?

The following question was deleted:
  1. What do you hope to accomplish in 5 years? 
```

### 3.2 Interview Phase
#### 3.2.1 Start Interview
You can start a specific interview session and it will enter the interview phase. It will capture all the remarks and answers that you type in during this interview session. It will also start the audio recorder to record the whole interview session.

The details of this interviewee that you have created during the initialization phase will be shown.

**Command**
```
start interview <name>
```
**Example**
```
> start interview Jane Doe
```
_Action:_ Initializes an interview session with Jane. Shows the uploaded CV (if any), and the list of interview questions in separate windows

#### 3.2.2 View Interview Questions
You can view the interview questions that you have set up for this interview session, to be asked to the interviewees.

**Command**
```
view questions
```
**Example**

_Output:_
```
Questions
Q1. What are your strengths and weaknesses?
Q2. What are your relevant experiences as a software developer?
Q3. What was your last salary?
```

#### 3.2.3 Record Remarks
You can add remarks throughout the interview sessions if you have any for the interviewee. This remarks will be mapped to the recording at this specific time such that you can remember what was discussed during this time. 

**Command**
```
<remarks>
```
**Example**
```
> Interesting family history, all of her family members are very rich. 
```
_Action:_ Stores this remark and maps it to the specific time when you enter it.

#### 3.2.4 Start and End Mark of A Question
You can indicate to start typing the answer for a question such that all the remarks that falls between the start and end of a question will be regarded as the answer to this question

**Command**
```
:start q1
<remarks>
<remarks>
:end q1
```
**Example**
```
> :start q1 
> Strengths: resilience, ambitious, good time-management
> Weakness: perfectionist, not detail-oriented
> :end q1
```
_Action:_ Stores these 2 lines of remark as the answer to Question 1.


#### 3.2.5 Score attributes
Each of the interviewees have different scoring attributes and the interviewer could update the attributes anytime during the interview.

**Command**
```
:<attributes> <score>
```
**Example**
```
> :Productivity 5
```
_Action:_ update the Productivity score for John Doe. 

#### 3.2.6 End interview
This is to allow the interviewers to end the interview session.

**Command**
```
end
```
**Example**
```
> end
```
_Action:_ Ending the interview session. 

_Output_: 
```
The interview session has ended.
```

### 3.3. Decision Phase

#### 3.3.1 Viewing Interviewee Reports
After interviewing a candidate, you can view the interview report any time by doing an open command, and we can close it after we finish examining the session. 

**Command**
```
open <interviewee>
close <interviewee>
```

**Example**
```
> open John Doe

... view some interview details ...

> close John Doe
```
_Output_: The entire list of remarks made during the interview, with their timestamps.

#### 3.3.2 Working in an Interviewee Report
After opening a report, you can scroll up and down to navigate through the remarks you made during the interview. In addition, you can zoom in on a particular moment in the interview by question number, or by the interview time.

In addition to viewing the remarks, you can also playback the interview audio record by specifying the --audio flag.

##### 3.3.2.1 Navigating by Question Number
Displays all the remarks made while the current interviewee was answering the given question, or plays back the audio from that period of time.

**Command**
```
q<question number> [--audio]
```

**Example**
```
> q4
```
_Output_:
```
Question 4
27.46 - Mentioned that he was fired from his previous job
27.59 - Was uncomfortable to share the reasons
```

##### 3.3.2.2 Navigating by Timestamp
Scrolls the list of remarks to the given time, or plays back the audio from that moment.

**Command**
```
at <time> [--audio]
```

**Example**
```
> at 30.00 --audio
```
_Action_: Plays audio starting at 30.00

##### 3.3.2.3 Stopping the Audio
Stops an ongoing audio playback.

**Command**
```
stop audio
```

#### 3.3.3 Find Best Candidates
After interviewing all the candidates, you can find the top n candidates based on either a particular attribute, the average of all the attributes. You can also make a weighted average for scoring.

**Command**
```
new weightage <weightage_name>
best <number of candidates>
best <number of candidates> [-a <attribute>]
best <number of candidates> [-w <weightage>]
```

**Example**
```
> best 3 -a courage
> new weightage weight1
ambition? 
> 0.4
teamwork?
> 0.6
> best 5 -w weight1
```

## 4. FAQ

Q: How do I transfer my data to another Computer?
A: Install the app in the other computer and overwrite the /data folder with /data folder that contains the data of your previous HireLah! folder.

## 5. Command Summary

**Start Session**
- `view session`  
- `delete session <session>`<br>e.g. delete session CEOInterview  
- `start session <session>`<br>e.g. start session CEOInterview  
- `new session <session>`<br>e.g. new session CEOInterview  


**Initialization Phase** 
- `new interviewee <fullname> [-a <alias>]`<br>e.g. new interviewee - Jane Doe -a Doe
- `delete interviewee <interviewee>`<br>e.g. delete interviewee Doe
- `list interviewee`
- `new attribute<attributes>` <br>e.g. new attribute teamwork
- `update attribute<attributes>` <br>e.g. update attribute leadership 
- `delete attribute<attributes>` <br>e.g. delete attribute productivity
- `list attribute`
- `add question <question>` <br>e.g. add question What are your relevant experiences?
- `update question <interviewee number><updated question>` <br>e.g. update question 1 What do you hope to accomplish in 5 years?
- `list question`
- `delete question <question number>` <br>e.g. delete question 1


**Interviewing Phase**
- Start:  `start interview <name>`<br>e.g. start interview Jane Doe
- View Questions: `view questions`
- Add Remarks: `remarks`<br>e.g.This guy is good at bluffing
- Start a Question: `:start q<no_of_question>`<br>e.g. :start q1
- End a question: `:end q<no_of_question>` <br>e.g. :end q1
- Score an attribute: `:<attribute> <score>`<br>e.g. :Agility 10
- End: end

**Decision Phase**

- Open an interviewee report: `open <interviewee>`<br>e.g. open Jane Doe
- Close an interviewee report: `close <interviewee>`<br>e.g. close Jane Doe
- Navigate to answer of interviewee: `q<question number> [--audio]`<br>e.g. q4
- Navigate to a time of interviewee: `at <time> [--audio]`<br>e.g. at 30.00 --audio
- Stops audio playing: `stop audio`
- Create a new weightage: `new weightage <weightage_name>`<br>e.g. new weightage extreme_intelligent
- Find the Best N candidates based on average attribute: `best <no_of_candidates>`
- Find the Best N candidates based on a particular attribute: `best <no_of_candidates> -p <attribute>`<br>e.g. best 3 strength
- Find the Best N candidates based on a weighted attribute: `best <no_of_candidates> -w <weightage>`<br>e.g. best 5 -w weightage1

