#!bin/bash

## Getting credentials from the user
read -p "Enter AWS Access Key: " AWS_ACCESS_KEY_ID
read -p "Enter AWS Secret Access Key:" AWS_SECRET_ACCESS_KEY

#################################### STAGING ####################################

### Configuration variables
KEY_PAIR_NAME="flask-app-code-deploy"
CLUSTER_NAME="code-300-cluster"
CONFIG_NAME="code-300-config"
REGION="us-east-1"
PROFILE_NAME="code-300-profile"
NUM_NODES=1
INSTANCE_TYPE="t2.medium"
SUBNET="subnet-078198f937094ddeb"
SECURITY_GROUP="sg-07afd98fa5a805d43"
VPC_ID="vpc-0222abd37d9ffeb37"

# Creating a new configuration.
ecs-cli configure --cluster $CLUSTER_NAME --default-launch-type EC2 --config-name $CONFIG_NAME --region $REGION
# Configuring the profile
ecs-cli configure profile --access-key "$AWS_ACCESS_KEY_ID" --secret-key "$AWS_SECRET_ACCESS_KEY" --profile-name $PROFILE_NAME

# Setting up the cluster
ecs-cli up --keypair "$KEY_PAIR_NAME" --capability-iam --size $NUM_NODES --instance-type $INSTANCE_TYPE \
           --cluster-config $CONFIG_NAME --ecs-profile $PROFILE_NAME \
           --subnets $SUBNET --security-group $SECURITY_GROUP \
           --vpc $VPC_ID

# Starting all the services
ecs-cli compose up --cluster-config $CONFIG_NAME --ecs-profile $PROFILE_NAME

#########################################################################################################################