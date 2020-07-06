package paper

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description '''
Represents creating a new paper

given:
    Some valid blank quizzes, quizzes sum is less than 5
when:
    a teacher assemble a new paper
then:
    create fail
'''

    request {
        url "/papers"
        method POST()
        headers {
            contentType applicationJson()
        }
        body(
                teacherId: '8jk4l-k0d9ie7-4jk89l-t88ijj6-h8i9040',
                quizzes: [
                        [
                                id   : '8jk4l-k0d9ie7-4jk89l-t88ijj6-h8ijsl1',
                                score: 5
                        ],

                ],
        )
        bodyMatchers {
            jsonPath('$.teacherId', byRegex('[a-zA-Z-0-9]{36}'))
            jsonPath("\$.['quizzes'].['id']", byRegex('[a-zA-Z-0-9]{36}'))
            jsonPath('$.quizzes', byType {maxOccurrence(4)})
            jsonPath("\$.['quizzes'].['score']", byRegex('100|[1-9][0-9]|[1-9]'))
        }
    }

    response {
        status BAD_REQUEST()
        headers {
            contentType applicationJson()
        }
    }
}