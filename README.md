# TraceId, SpanID For Springboot

모놀리틱 Springboot 3.x버전과 kotlin으로 구성한 환경입니다

간단한 controller 2개 구성하여, span id, trace id가 어떻게 작동하는지 확인할 수 있습니다

spring boot 가 아니므로 톰캣을 구성한 실행합니다

localhost:9090/test localhost:9090/jsh

를 호출하여 controller, service 레이어별로 어떻게 id들을 생성하는지 확인해보세요
