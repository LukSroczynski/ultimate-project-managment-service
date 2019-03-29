package pl.braintelligence.projectmanager.team.domain

import pl.braintelligence.projectmanager.base.BaseUnitTest
import pl.braintelligence.projectmanager.core.ports.in.TeamManager
import pl.braintelligence.projectmanager.core.team.configuration.TeamConfiguration
import pl.braintelligence.projectmanager.core.team.domain.Team
import pl.braintelligence.projectmanager.core.team.exception.EntityAlreadyExistsException
import pl.braintelligence.projectmanager.core.team.exception.MissingTeamException

class TeamValidationTest extends BaseUnitTest {

    private TeamManager teamService = new TeamConfiguration().teamManager()

    def "Should create a team"() {
        given:
        def teamName = "valid team name"

        when:
        def team = new Team(teamName)

        then:
        team != null
    }

    def "Should throw exception when team does not exist"() {
        when:
        teamService.addMemberToTeam("non-existent team name", teamMemberDto)

        then:
        def thrown = thrown(MissingTeamException.class)
        thrown.message == "Team does not exist."
    }

    def "Should throw an exception when team name is empty"() {
        when:
        new Team(teamName)

        then:
        def ex = thrown(IllegalArgumentException.class)
        ex.message == "Empty team name."

        where:
        teamName << ['', ' ']
    }

    def "Should not create team that already exists"() {
        given: "create a team"
        teamService.createTeam(newTeamDto)

        when: "create another team with the same name"
        teamService.createTeam(newTeamDto)

        then:
        def thrown = thrown(EntityAlreadyExistsException.class)
        thrown.message == "Team already exist."
    }
}
