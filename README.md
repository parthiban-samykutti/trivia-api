
| URI                              | HTTP Method | HTTP Status | DESC                                                      |   |
|----------------------------------|-------------|-------------|-----------------------------------------------------------|---|
| /api/trivia/question             | POST        | 201         | Create and Return the question                            |   |
| /api/trivia/question             | GET         | 200         | Get all the questions                                     |   |
| /api/trivia/question/{id}        | GET         | 200         | Get a question by it's id                                 |   |
| /api/trivia/question             | PUT         | 204         | Update the question                                       |   |
| /api/trivia/question/{id}        | DELETE      | 204         | Delete the question                                       |   |
| /api/trivia/answer               | POST        | 201         | Create and return the answer                              |   |
| /api/trivia/answer/question/{id} | GET         | 200         | Return the all the multiple choice answers for a question |   |
| /api/trivia/quiz                 | GET         | 200         | Return 10 random questions with answers                   |   |
|                                  |             |             |                                                           |   |