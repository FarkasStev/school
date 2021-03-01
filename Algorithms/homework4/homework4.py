    
memos = {}


def min_sections(sections, target):
    if memos[sections] != None:
        return memos[sections]
    else:
        if len(sections) == 1:
            if target % sections[0] == 0:
                return [target / sections[0]]
            else: 
                return None
    
        best = None

        for n in range(0, target // sections[0] + 1):
            with_choice_of_n = min_sections(sections[1:], target - n * sections[0])
            if with_choice_of_n != None and (best == None or sum(best) > sum(with_choice_of_n) + n):
                best = [n] + with_choice_of_n

        return best

#################################
# Main Program
#################################

sections = [int(x) for x in input().split()]
target   = int(input())

rocket_selection = min_sections(sections, target)


if rocket_selection != None:
    for i in range(len(rocket_selection)):
        print(f"{int(rocket_selection[i])} of length {sections[i]}")

    print(f"{int(sum(rocket_selection))} rocket sections minimum")
else:
    print("It is not possible to construct the rocket from the given sections.")
