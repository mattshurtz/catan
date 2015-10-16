# -*- mode: ruby -*-
# vi: set ft=ruby :


#########################################
# Vagrant is a fast way of setting up virtual machines
# We can use this for testing purposes, as this enviroment is more like what the TA's will be using
# To use you must install:
## VirtualBox | https://www.virtualbox.org/wiki/Downloads
## Vagrant | https://www.vagrantup.com/downloads.html
# The documentation for Vagrant is found here: https://docs.vagrantup.com/v2/
# Quickstart
## After you have installed the above programs, you can quickly start using this file with the following commands from the catan directory.
## vagrant up | starts the virtual machine. If this is the first time starting the machine it will take some time to download the box and install the software specified in this config file.
## vagrant destroy | destroy the vagrant box.
## vagrant halt | shutsdown the box. can be turned on by vagrant up
## vagrant provision | reperforms the shell commands found at the end of this file.
#########################################

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.
Vagrant.configure(2) do |config|
  # The most common configuration options are documented and commented below.
  # For a complete reference, please see the online documentation at
  # https://docs.vagrantup.com.

  # Every Vagrant development environment requires a box. You can search for
  # boxes at https://atlas.hashicorp.com/search.
  config.vm.box = "box-cutter/ubuntu1404-desktop"

  # Disable automatic box update checking. If you disable this, then
  # boxes will only be checked for updates when the user runs
  # `vagrant box outdated`. This is not recommended.
  # config.vm.box_check_update = false

  # Create a forwarded port mapping which allows access to a specific port
  # within the machine from a port on the host machine. In the example below,
  # accessing "localhost:8080" will access port 80 on the guest machine.
  config.vm.network "forwarded_port", guest: 8081, host: 8081

  # Create a private network, which allows host-only access to the machine
  # using a specific IP.
  # config.vm.network "private_network", ip: "192.168.33.10"

  # Create a public network, which generally matched to bridged network.
  # Bridged networks make the machine appear as another physical device on
  # your network.
  # config.vm.network "public_network"

  # Share an additional folder to the guest VM. The first argument is
  # the path on the host to the actual folder. The second argument is
  # the path on the guest to mount the folder. And the optional third
  # argument is a set of non-required options.
  # config.vm.synced_folder "../data", "/vagrant_data"

  # Provider-specific configuration so you can fine-tune various
  # backing providers for Vagrant. These expose provider-specific options.
  # Example for VirtualBox:
  #
   config.vm.provider "virtualbox" do |vb|
  #   # Display the VirtualBox GUI when booting the machine
     vb.gui = true
  #
  #   # Customize the amount of memory on the VM:
  #   vb.memory = "1024"
   end
  #
  # View the documentation for the provider you are using for more
  # information on available options.

  # Define a Vagrant Push strategy for pushing to Atlas. Other push strategies
  # such as FTP and Heroku are also available. See the documentation at
  # https://docs.vagrantup.com/v2/push/atlas.html for more information.
  # config.push.define "atlas" do |push|
  #   push.app = "YOUR_ATLAS_USERNAME/YOUR_APPLICATION_NAME"
  # end

  # Enable provisioning with a shell script. Additional provisioners such as
  # Puppet, Chef, Ansible, Salt, and Docker are also available. Please see the
  # documentation for more information about their specific syntax and use.
   config.vm.provision "shell", inline: <<-SHELL
     sudo apt-get update
     sudo apt-get -y install default-jre
     sudo apt-get -y install default-jdk
     sudo apt-get -y install ant
     sudo apt-get -y upgrade
   SHELL
end
