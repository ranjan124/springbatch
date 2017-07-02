# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|  
  config.vm.box = 'bento/ubuntu-16.04'

  config.ssh.forward_x11 = true

  config.vm.network "private_network", ip: "192.168.99.101"
  
  config.vm.define "dev" do |dev|
    dev.vm.provider "virtualbox" do |vm|
    	vm.memory = 4096
        vm.cpus = 1
    end
  end
end

