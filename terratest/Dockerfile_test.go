package test

import (
	"testing"

	"github.com/gruntwork-io/terratest/modules/docker"
	"github.com/stretchr/testify/assert"
	"github.com/google/uuid"
)

func TestValidDockerTestMavenProject(t *testing.T) {
	// Configure the tag to use on the Docker image.
	tag := uuid.New().String()
	buildOptions := &docker.BuildOptions{
		Tags: []string{tag},
	}

	// Build the Docker image.
	docker.Build(t, "../", buildOptions)

	//Test if app jar present
	optsForAppDir := &docker.RunOptions{Command: []string{"ls"}}
	outputForAppDir := docker.Run(t, tag, optsForAppDir)
	assert.Contains(t, outputForAppDir, "RobotRESTApp")
}