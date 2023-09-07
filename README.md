## Requirements
The application is a simulation of a toy robot moving on a square tabletop, of dimensions ***5 x 5*** units. There are no other obstructions on the table surface.
The robot is free to roam around the surface of the table, but must be prevented from falling to destruction. Any movement that would result in the robot
falling from the table must be prevented, however further valid movement commands must still be allowed.

The application can read in commands of the following form:

PLACE X,Y,F

MOVE

LEFT

RIGHT

REPORT

PLACE will put the toy robot on the table in position X,Y and facing NORTH, SOUTH, EAST or WEST.

MOVE will move the toy robot one unit forward in the direction it is currently facing.

LEFT and RIGHT will rotate the robot 90 degrees in the specified direction without changing the position of the robot.

REPORT will announce the X,Y and F of the robot.

The robot that is not on the table can choose to ignore the MOVE, LEFT, RIGHT and REPORT commands.

The robot must not fall off the table during movement. This also includes the initial placement of the toy robot.
Any move that would cause the robot to fall must be ignored.

## Assumptions done on top of the above requirements while designing the REST APIs
1. When a robot is already in the (1,1) position, the API will not create a new or update an existing robot to the (1,1) position. The API call will report this as a duplicate move.

2. The robot positions issued through the REST API are stored in an in-memory database. This is required for future updates of existing robot positions, reporting, etc.

3. As the requirement is 5*5 grid, the X and Y cor-ordinates range from 0 to 4. With regards to the direction, visualise that the grid is in front of you and when you look at the grid, you are facing north.

Please refer to the ***REST API Technical design document under documentation folder*** for technical design details.

## Application implementation details:

1. This is a Spring Boot application. pom.xml configured to download all the dependencies. Built using Java 17.

2. Run the application using an IDE of your choice. The command to run from CLI is:
`mvn clean spring-boot:run`

4. All the inputs and outputs have been realised using REST API. Please refer to the ***REST API Technical design document under documentation folder*** for technical design details.

5. The ***REST API test results*** are also under documentation folder in the repository. Testing done through Postman and few JUnits written too.