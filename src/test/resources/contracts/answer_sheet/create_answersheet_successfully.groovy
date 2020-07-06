package answer_sheet

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.MatchingTypeValue

Contract.make {
    description '''
Represents creating a new answer sheet

given:
    Valid examinationId
when:
    a student begin examination and create a new answer sheet
then:
    this student should get valid answer sheet info
'''

    request {
        url $(consumer("/examinations/${regex('[a-zA-Z-0-9]{36}')}/answer-sheets"),
                producer("/examinations/9idk4-lokfu-jr874j3-h8d9j4-hor82kd77/answer-sheets"))
        method POST()
        body(
                studentId: '8jk4l-k0d9ie7-4jk89l-t88ijj6-h8i9040'
        )
        bodyMatchers {
            jsonPath('$.studentId', byRegex('[a-zA-Z-0-9]{36}'))
        }
    }

    response {
        status CREATED()
        headers {
            contentType applicationJson()
        }
        body(
                answerSheetId: '8jk4l-k0d9ie7-4jk89l-t99ijj6-h8i9040',
                examinationId: '8jk4l-k0d9ie7-4jk89k-t99ijj6-h8i9040',
                studentId: '8jk4l-k0d9ie7-4jk89l-t88ijj6-h8i9040',
                duration: 120,
                answer: null,
                startedTime: '2020-06-29T12:00:00',
                submittedTime: null,
        )
        bodyMatchers {
            jsonPath('$.answerSheetId', byRegex('[a-zA-Z-0-9]{36}'))
            jsonPath('$.examinationId', byRegex('[a-zA-Z-0-9]{36}'))
            jsonPath('$.studentId', byRegex('[a-zA-Z-0-9]{36}'))
            jsonPath('$.startedTime', byTimestamp())
        }
    }
}