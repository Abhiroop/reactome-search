git filter-branch --commit-filter '
        if [ "$GIT_COMMITTER_NAME" = "Abhiroop" ];
        then
                GIT_COMMITTER_NAME="Abhiroop";
                GIT_AUTHOR_NAME="Abhiroop";
                GIT_COMMITTER_EMAIL="asiamgenius@gmail.com";
                GIT_AUTHOR_EMAIL="asiamgenius@gmail.com";
                git commit-tree "$@";
        else
                git commit-tree "$@";
        fi' HEAD