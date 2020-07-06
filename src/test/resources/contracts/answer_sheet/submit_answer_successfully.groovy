package answer_sheet

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description '''
Represents submit a answer sheet

given:
    Valid answer sheet
when:
    a student submit answer in duration
then:
    this student should get the answer sheet info
'''

    request {
        url $(consumer("/examinations/${regex('[a-zA-Z-0-9]{36}')}/answer-sheets/${regex('[a-zA-Z-0-9]{36}')}"),
                producer("/examinations/9idk4-lokfu-jr874j3-h8d9j4-hor82kd77/answer-sheet/9idk4-lokfu-jr874j3-h8d9j4-ho7kdl009"))
        method PUT()
        body(
                studentId: regex('8jk4l-k0d9ie7-4jk89l-t88ijj6-h8i9040'),
                answer: anyNonBlankString(),
                startedTime: '2020-06-27T09:00:00',
                submittedTime: '2020-06-27T10:30:00',
        )
        bodyMatchers {
            jsonPath('$.studentId', byRegex('[a-zA-Z-0-9]{36}'))
            jsonPath('$.answer', byRegex('.{1,4000}'))
            jsonPath('$.startedTime', byTimestamp())
            jsonPath('$.submittedTime', byTimestamp())
        }
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body(
                answerSheetId: regex('[a-zA-Z-0-9]{36}'),
                examinationId: regex('[a-zA-Z-0-9]{36}'),
                studentId: fromRequest().body('$.studentId'),
                answer: fromRequest().body('$.answer'),
                startedTime: fromRequest().body('$.startedTime'),
                submitedTime: fromRequest().body('$.submittedTime')
        )
    }
}