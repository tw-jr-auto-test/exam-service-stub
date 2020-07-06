package answer_sheet

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description '''
Represents submit a answer sheet

given:
    Valid answer sheet
when:
    a student submit answer expired
then:
    this student should get an 400 error
'''

    request {
        url $(consumer("/examinations/${regex('[a-zA-Z-0-9]{36}')}/answer-sheets/9idk4-lokfu-jr874j3-h8d9j4-ho7kdl8jh"),
                producer("/examinations/9idk4-lokfu-jr874j3-h8d9j4-hor82kd77/answer-sheet/9idk4-lokfu-jr874j3-h8d9j4-ho7kdl8jh"))
        method PUT()
        body(
                studentId: '8jk4l-k0d9ie7-4jk89l-t88ijj6-h8i9041',
                answer: anyNonBlankString(),
                startedTime: '2020-06-27T09:00:00',
                submittedTime: '2020-06-27T12:30:00'
        )
        bodyMatchers {
            jsonPath('$.studentId', byRegex('[a-zA-Z-0-9]{36}'))
            jsonPath('$.answer', byRegex('.{1,4000}'))
        }
    }

    response {
        status BAD_REQUEST()
    }
}